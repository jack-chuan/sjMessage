var Utils = {
    /**
     * 返回url的参数值
     * @param name 参数
     * @returns {any} 参数值
     */
    getUrlParam : function(name){
        var reg = new RegExp('(^|&)'+name+'=([^&]*)(&|$)');
        var result = window.location.search.substr(1).match(reg);
        return result ? decodeURIComponent(result[2]):null;
    }

};
function getRootPath() {
	   var strPath = window.document.location.pathname;
	  var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
	  if(postPath != 'sjMessage-api'){
		  postPath = '';
	  }
	   return postPath;
	 } 