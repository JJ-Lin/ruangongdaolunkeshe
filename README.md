能力规范文稿管理系统
 
版本号	1.0.0    
更新时间	2018.7.3    
提交人	许奕璋    
提交日期	2018.7.3    
前期版本	   
当前版本	1.0.0   

1引言   
1.1编写目的
本需求分析说明书对本项目第一阶段的內容进行分析，对需求细节和实现方式进行了较为详细的阐述。本需求说明书供业务和科技部门人员、软件需求提供人员、软件的概要设计人员、软件的开发人员、软件的测试人员使用，并作为产品验收确认的依据

1.2预期使用者   
用户    
管理者    
开发人员    
测试人员

1.3产品范围
利用JAVA，JSP语言建立平台界面，完成相关操作按钮、下拉框、指令等所有对应操作。

2综合描述   
2.1用户类和特征
主要用户是浏览该网页的所有用户。用户没有专业的计算机知识，所以需要一个友好简单的界面。用户通过浏览该网页，可以对一些提案或文档进行审核。
尽可能的让用户不经过任何培训就能够较为熟练的应用此系统。
本系统的预期使用时间：7*24小时

2.2软硬件环境需求   
操作系统：Windows    
数据库：SQL    
开发工具：IntelliJ IDEA

2.3假设和约束依赖   
(1)各个模块之间稳定协作
系统主要会分为几个模块，各个模块之间的稳定协作需要得到保证，保证系统7*24小时的稳定运行，尽量只保持周期性的维护。
(2)系统安全
系统的安全是当前网络环境下的一个重要要求，系统的安全不仅关系着自身的盈利等，还关系着用户的各种信息。


3外部接口需求   
3.1用户界面
要求尽量简洁的界面，争取直观的传递给各用户尽量多的讯息。

3.2通讯接口
遵循各种网络协议，满足各种浏览器的需求。

4系统功能需求   
4.1说明和优先级
进入本系统之前，需要有登录和注册两个功能。操作根据用户名和密码进行登录，非会员点击注册按钮填写入会申请，等待管理者审核。
进入之后，本系统有前后两个模块，根据最终用户具有的不同功能将用户分为两类：
①用户（写作人员）：提案查询、提案编制、规范查询、规范编制、信息维护
②管理员：维持系统的正常，管理审核信息，以及所有写作人员的功能
前后模块的主要功能是信息发布
后台数据管理功能主要用于完成远程数据库服务器维护功能，包括接收数据的分类与信息的添加、删除、修改等功能，以及对网站操作员的管理等。此外网站所有动态信息维护也均由网站后台管理系统完成。
优先级：管理员 > 用户

4.2功能需求   
进入系统之前：   
①注册：
注册人拥有9个属性（其中包含一个隐藏的属性，即用于判断注册人的状态及身份，0表示注册人还未通过管理员的审核，1表示注册人通过了管理员的审核且其身份为写手用户，2表示注册人通过了管理员的审核且其身份为管理员用户），其中姓名、性别（字符型，M/m表示男性，F/f表示女性）、出生日期、联系方式（11位数字，可用string）、所属行业分会和所属专委会是必填信息，不可为空，必须要填写完整才可以允许进行注册的申请操作。
而地址和推荐人部分不是必填项。但如果具有推荐人，将能够提高注册申请的成功性。
注册人点击提交以后，跳转到另一个界面。系统首先判断其6个必填项是否为null，如果为null，则注册失败，并提示注册者；如果不是null且6个必填项皆按标准格式输入，则申请成功，等待管理员的审核。
②登录：
如果是已经完成注册申请并通过管理人员验证的用户，将可以通过首页登录区域直接进行登录操作。
首先，用户输入用户名和密码，并点击登录。系统会先判断用户输入的用户名和密码是否为null，如果为null，则提示用户；如果不为null，则调用数据库，来判断该用户输入的用户名所对应的密码是否正确，正确则判断用户身份并进入所对应的系统，错误则提示用户错误。

进入系统之后：
①写手用户功能：提案查询、提案编制、规范查询、规范编制、信息维护
（1）提案查询
     写者可通过提案的编号、名称和作者姓名这三类关键字进行范围锁定，系统按提案编号降序排列。系统以列表的形式进行展示。列表的内容包括编号、提案名称、作者、截止日期、状态、附议数、反对数、全选。写者点击提案名称可链接到此条提案详情，包括提案ID、提案名称、提案作者、截止日期、提案内容、评论区，写者可在空白框中进行评论，可选择“附议”或“反对”键进行表态，最后提交。评论区内容包括评论人、评论内容和时间。查询结束后可返回到主界面。
     注：只有已登录用户才能够进行查询。  
（2）提案编制
     写者填写相关提案的名称和提案内容，可进行打印、保存、提交。页面下方展示提案列表，写者可以看到自己提交的所有提案ID、名称、提交时间和对应的状态（同一建议人处于意见征集期的提案不能超过3项），但可以选择撤销在意见征集期的提案（撤销就不算在那3条之内）。//列表可含全选功能。
     注：仅写者有此权限。  
（3）信息维护  
     对用户（写手和管理员）进行信息管理
     
②管理员用户功能：   
（1）管理审核信息
管理员登录系统——>系统提示管理员有新的申请信息——>管理员查看新信息——>系统展示信息，且有推荐人的信息会有明确标注——>管理员点击通过——>系统把该用户信息转入会员信息表
（2）写手用户可执行的所有功能

通过输入用于一定访问权限的用户名和密码，在后台添加新的信息，对已有信息进行修改或删除。                 
                  
5其他非功能需求    
5.1性能需求
时间特性：页面刷新时间不能超过3秒，页面直接的跳转不超过4秒。满足用户需求快速得到信息的要求，页面的优化要做好，使得每个用户能感觉到到能够流畅迅速的查找到自己所需要的信息。

5.2技术选型需求   
①根据业务需求选用SQL数据库   
②使用Java Web技术体系，前段技术选型HTML
③开发工具选用IntelliJ_IDEA，是一个Java语言开发的集成环境
③版本控制工具选用GitHub，管理项目任务分配和进度安排

5.2软件质量属性
易用性由于易学性，或者可移植性由于有效性。

5.3用户文档
暂无编写需求
