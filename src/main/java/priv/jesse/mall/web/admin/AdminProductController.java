package priv.jesse.mall.web.admin;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import priv.jesse.mall.entity.Classification;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.ClassificationService;
import priv.jesse.mall.service.ProductService;
import priv.jesse.mall.utils.DownloadUtils;
import priv.jesse.mall.utils.FileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ClassificationService classificationService;

    @RequestMapping("/toList.html")
    public String toList() {
        return "admin/product/list";
    }

    @RequestMapping("/toAdd.html")
    public String toAdd() {
        return "admin/product/add";
    }

    @RequestMapping("/toEdit.html")
    public String toEdit(int id, Map<String, Object> map) {
        Product product = productService.findById(id);
        Classification classification = classificationService.findById(product.getCsid());
        product.setCategorySec(classification);
        map.put("product", product);
        return "admin/product/edit";
    }

    @ResponseBody
    @RequestMapping("/list.do")
    public ResultBean<List<Product>> listProduct(int pageindex,
                                                 @RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        Pageable pageable = new PageRequest(pageindex, pageSize, null);
        List<Product> list = productService.findAll(pageable).getContent();
        return new ResultBean<>(list);
    }

    @ResponseBody
    @RequestMapping("/getTotal")
    public ResultBean<Integer> getTotal() {
        Pageable pageable = new PageRequest(1, 15, null);
        int total = (int) productService.findAll(pageable).getTotalElements();
        return new ResultBean<>(total);
    }

    @RequestMapping("/del.do")
    @ResponseBody
    public ResultBean<Boolean> del(int id) {
        Product p = productService.findById(id);
        String[] urls=p.getImage().split("/");
        String path =System.getProperty("user.dir")+"\\file\\"+urls[urls.length-1];
        FileUtil.deleteDir(path);
        productService.delById(id);
        return new ResultBean<>(true);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add.do")
    public void add(MultipartFile image,
                    String title,
                    Double marketPrice,
                    Double shopPrice,
                    int isHot,
                    String desc,
                    int csid,
                    HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        Product product = new Product();
        product.setTitle(title);
        product.setMarketPrice(marketPrice);
        product.setShopPrice(shopPrice);
        product.setDesc(desc);
        product.setIsHot(isHot);
        product.setCsid(csid);
        product.setPdate(new Date());
        String imgUrl = FileUtil.saveFile(image);
        product.setImage(imgUrl);
        int id = productService.create(product);
        if (id <= 0) {
            request.setAttribute("message", "添加失败！");
            request.getRequestDispatcher("toAdd.html").forward(request, response);
        } else {
            response.sendRedirect("toList.html");
            //request.getRequestDispatcher("toEdit.html?id=" + id).forward(request, response);
        }
    }


    @RequestMapping(method = RequestMethod.POST, value = "/update.do")
    public void update(int id,
                       String title,
                       Double marketPrice,
                       Double shopPrice,
                       String desc,
                       int csid,
                       int isHot,
                       MultipartFile image,
                       HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
        Product product = productService.findById(id);
        product.setTitle(title);
        product.setMarketPrice(marketPrice);
        product.setShopPrice(shopPrice);
        product.setDesc(desc);
        product.setIsHot(isHot);
        product.setCsid(csid);
        product.setPdate(new Date());
        String imgUrl = FileUtil.saveFile(image);
        if (StringUtils.isNotBlank(imgUrl)) {
            product.setImage(imgUrl);
        }
        boolean flag = false;
        try {
            productService.update(product);
            flag = true;
        } catch (Exception e) {
            throw new Exception(e);
        }
        if (!flag) {
            request.setAttribute("message", "更新失败！");
        }
        response.sendRedirect("toList.html");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/img/{filename:.+}")
    public void getImage(@PathVariable(name = "filename", required = true) String filename,
                         HttpServletResponse res) throws IOException {
        File file = new File("file/" + filename);
        if (file != null && file.exists()) {
            res.setHeader("content-type", "application/octet-stream");
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            res.setContentLengthLong(file.length());
            Files.copy(Paths.get(file.toURI()), res.getOutputStream());
        }
    }

    @RequestMapping("/upload/product")
    @ResponseBody
    public ResultBean uploadProduct(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
        System.out.println(file.getOriginalFilename());
        InputStream inputStream = file.getInputStream();

        Workbook wookbook = null;
        Sheet sheet = null;
        String fileName = file.getOriginalFilename();
        LinkedList<String> fileNames = new LinkedList<>();
        try {
            //2007版本的excel，用.xlsx结尾
            wookbook = new XSSFWorkbook(inputStream);//得到工作簿
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Map<String, PictureData> maplist = null;

        sheet = wookbook.getSheetAt(0);
        // 判断用07还是03的方法获取图片
        if (fileName.endsWith(".xls")) {
            maplist = FileUtil.getPictures1((HSSFSheet) sheet);
        } else if (fileName.endsWith(".xlsx")) {
            maplist = FileUtil.getPictures2((XSSFSheet) sheet);
        }

        fileNames = FileUtil.printImg(maplist);
        System.out.println(fileNames.get(0));
        System.out.println("************");
        int totalRowNum = sheet.getLastRowNum();
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= totalRowNum; i++) {
            Product product = new Product();
            //设置csid
            String class_name = sheet.getRow(i).getCell(0).getStringCellValue();
            Classification classification = classificationService.findByName(class_name);
            product.setCsid(classification.getId());
            //设置title
            String title = sheet.getRow(i).getCell(1).getStringCellValue();
            product.setTitle(title);
            //设置是否热卖
            String is_sale = sheet.getRow(i).getCell(2).getStringCellValue();
            product.setIsHot(is_sale.equals("是") ? 1 : 0);
            //设置市场价格
            Double mark_price = sheet.getRow(i).getCell(3).getNumericCellValue();
            product.setMarketPrice(mark_price);
            //设置平台价格
            Double shop_price = sheet.getRow(i).getCell(4).getNumericCellValue();
            product.setShopPrice(shop_price);
            //设置商品描述
            String des = sheet.getRow(i).getCell(5).getStringCellValue();
            product.setDesc(des);
            //设置商品图片
            String baseUrl = "/mall/admin/product/img/";
            product.setImage(baseUrl + fileNames.get(i - 1));
            //设置时间
            product.setPdate(new Date());
            //数据插入
            products.add(product);
        }
        productService.saveAll(products);
        return new ResultBean(200);
    }


    @RequestMapping("downloadExcel")
    public void downloadExcel(HttpServletResponse response) throws Exception {
        //1.获取所有2级分类
        List<Classification> classifications = classificationService.findAll();
        List<String> cnames = new ArrayList<>();
        for (Classification c : classifications) {
            cnames.add(c.getCname());
        }
        String cs[] = cnames.toArray(new String[cnames.size()]);
        System.out.println(cs);
        //2.加载模板
        Resource resource = new ClassPathResource("muban.xlsx");
        FileInputStream fileInputStream = new FileInputStream(resource.getFile());
        //3.根据模板创建工作簿
        Workbook wb = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = ((XSSFWorkbook) wb).getSheetAt(0);
        //4.设置下拉框
        // 设置第一列的1-100行为下拉列表
        CellRangeAddressList regions = new CellRangeAddressList(0, 100, 0, 0);
        CellRangeAddressList regios1 = new CellRangeAddressList(0, 100, 2, 2);
        // 创建下拉列表数据
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        DataValidationConstraint createExplicitListConstraint = dvHelper.createExplicitListConstraint(cs);
        DataValidation createValidation = dvHelper.createValidation(createExplicitListConstraint, regions);

        String[] is_hots = {"是", "否"};
        DataValidationConstraint createExplicitListConstraint1 = dvHelper.createExplicitListConstraint(is_hots);
        DataValidation createValidation1 = dvHelper.createValidation(createExplicitListConstraint1, regios1);
        // 绑定
        sheet.addValidationData(createValidation);
        sheet.addValidationData(createValidation1);
        //5.下载
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        DownloadUtils.download(os, response, "商品上传模板.xlsx");
    }
}
