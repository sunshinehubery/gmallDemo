# gmallDemo
gmall-parent:项目父工程，父依赖使用spring boot 2.1.7  
gmall-api:负责管理项目中所有的接口和bean  
gmall-common-utils:项目中的通用框架，所有项目都需要引入  
gmall-service-utils:web后端需要的框架（mybatis，mysql，redis）  
gmall-web-utils:web前端需要的框架（jsp，thymeleaf，cookie工具类）  
gmall-user:port-8080，用于测试的Demo,并没有使用SOA  
gmall-user-service:port-8070,web后端（provider），服务名称user-service  
gmall-user-web:port-8080,web前端（consumer），服务名称user-web  
gmall-manage-service:port-8071,服务名称manage-service  
gmall-manage-web:port-8081,服务名称manage-web  
fastfds整合spring boot  
先从github上下载fastdfs-client-java  
git clone https://github.com/happyfish100/fastdfs-client-java  
导入Maven，将fdfs的客户端打包到本地的maven仓库  
最后将整合到需要的子项目中  

