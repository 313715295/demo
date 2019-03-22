# demo
架构上是springboot+mybatis+redis+mysql+activeMQ+zookeeper+dubbo，前端是js+jquery+bootstrap，具体版本可以看pom。

只有四张表，tea是产品~~ user是用户  order是订单 orderItem是订单项。

项目分为两个模块 demo-basic和demo-modules，其中basic为基础模块，是使用到的工具以及数据库相关实体和业务操作。

modulse为具体的功能模块。包括功能服务和web层。
admin：管理员操作商品以及订单列表的业务实现；
cart： 商品列表和加入购物车的相关操作的具体业务实现；
order：订单相关的具体业务实现；
sigin：用户登录注册具体的业务实现；

web层负责调用以上4个服务，获取数据后返回模板渲染页面。静态页面和js都在web层，其中redis缓存操作都在servicImpl相关操作中，
activeMQ在购物车提交订单中有使用（用法其实错误的，因为库存更新成功后，丢入消息队列，如果添加订单失败无法回滚库存~
有兴趣的可以了解下分布式事务）。

和实际工作出入还是比较大的，只适合初学者当个demo参考下。

步骤：
需要导入demo.sql创建相关表。数据库相关配置在demo-dao中的MybatisConfig中，当时因为不想每个服务都去配置数据库，
所以把这个弄成一个bean了，然后公用这个bean。其中需要设置sqlSessionFactoryBean.setVfs(SpringBootVFS.class)以及服务层启动类中
添加scanBasePackages。

安装zookeeper、redis、activeMQ，具体可以网上看攻略。确保都启动后，启动四个服务，成功注册至zookeeper后就可以启动web服务了。

看代码可以从demo-web中的几个controller开始看，然后逐步追踪业务实现，这个是使用的模板引擎渲染页面，当前大部分都使用json做数据交互。
