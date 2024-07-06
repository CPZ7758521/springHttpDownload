可以对接前端页面，返回数据流给前端的Spring项目，可以通过Http请求下载，网页内容

在html中这样使用

window. Location. href ="http://10.89.79.63:9086/api/downLoad/directDownLoad"+
"?urL="+targetUrL+
"&fileName=" + fileName +
"&savePath=" + LinuxSavePath;