WebLogAspect 日志切面功能：打印入参出参；自动放置tenantId；捕获controller层接口抛出的异常
controller层不需要tenantId的方法可加NotTenantId注解，日志切面将不会放置tenantId

自定义的持久层文件放在custom文件夹里，防止表变更后自动生成持久层时覆盖

biz层调用持久层，参考ExampleBiz的例子
service层对外暴露dubbo接口，这期不用

