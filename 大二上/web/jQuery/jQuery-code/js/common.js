/*用$更简单
function $(id){
	return document.getElementById(id);
}
*/
function createXhr(){
	var xhr = null;
	//如果支持标准创建
	if (window.XMLHttpRequest){
        xhr = new  XMLHttpRequest();
	}
	else{
		//IE8以下的创建方式
		xhr = new ActiveXOject("Microsoft.XMLHttp");
	}
		return xhr;
}