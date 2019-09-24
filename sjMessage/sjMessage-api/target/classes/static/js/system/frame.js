$(function(){
    var iframe = Utils.getUrlParam("iframe");
    var isRead = Utils.getUrlParam("isRead");
    if(null != isRead && "" != isRead){
        $("#frameBody").attr("src",getRootPath()+"/pageJump/"+iframe+"?isRead="+isRead);
    } else {
        $("#frameBody").attr("src",getRootPath()+"/pageJump/"+iframe);
    }
    $('.Sidebar ul li a').each(function(){
        $(this).removeClass('on');
    });
    $("#"+iframe).addClass('on');

    function SidebarToggle() {
        var a = $('.Sidebar ul li a');
        a.bind('click', function () {
            var this_ = $(this);
            this_.hasClass('on') ? a.removeClass('on') : a.removeClass('on'); this_.addClass('on');
        });
    }
    //	菜单切换
    SidebarToggle();
});