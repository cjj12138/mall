function changePage(page) {
    $(".page-sidebar-menu li").removeClass("active");
    if ('categorySec' == page) {
        $("#category").addClass("active").find("a").eq(0).append($('<span class="selected"></span>'));
        $("#categorysec").addClass("active");
        $("#mainFrame").attr("src", "classification/toList.html?type=2");
        return;
    } else if ('category' == page) {
        $("#category").addClass("active").find("a").eq(0).append($('<span class="selected"></span>'));
        $("#category_1").addClass("active");
        $("#mainFrame").attr("src", "classification/toList.html?type=1");
        return;
    } else {
        $("#" + page).addClass("active").find("a").eq(0).append($('<span class="selected"></span>'));
    }
    $("#mainFrame").attr("src", page + "/toList.html");
}