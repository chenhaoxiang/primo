<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Responsive Admin Dashboard Template">
    <meta name="keywords" content="admin,dashboard">
    <meta name="author" content="stacks">
    <!-- The above 6 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>Neptune - Responsive Admin Dashboard Template</title>

    <!-- Styles -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"
          rel="stylesheet">
    <link href="/assets/primo/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/primo/plugins/perfectscroll/perfect-scrollbar.css" rel="stylesheet">
    <link href="/assets/primo/plugins/pace/pace.css" rel="stylesheet">
    <link href="/assets/primo/plugins/highlight/styles/github-gist.css" rel="stylesheet">
    <link href="/assets/primo/plugins/datatables/datatables.min.css" rel="stylesheet">

    <!-- Theme Styles -->
    <link href="/assets/primo/css/main.min.css" rel="stylesheet">
    <link href="/assets/primo/css/custom.css" rel="stylesheet">
    <!--    图表自定义的css-->
    <style>
        /* 单元格连续纯字母数字强制换行显示 */
        .wordwrap {
            word-wrap: break-word;
            word-break: break-all;
            overflow: hidden;
        }

        /* 超长文字单元格省略号显示 */
        .ellipsis {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            -o-text-overflow: ellipsis;
        }

        /*超长文本省略展示，多行展示*/
        .ellipsis_line {
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 3;
            overflow: hidden;
        }

        /*红色星号*/
        .red-asterisk {
            color: red;
        }

        /* form边距调整 */
        form {
            margin: 5px 5px 0 0;
        }

        /*
        fixed的表格才可以使单元格显示省略号
        该CSS会导致列未进行设置宽时出现单行挤兑的情况。
        这个可以根据所有列宽来进行设置。
        如果没有设置列宽和省略号的，则不设置这个。如果设置了列宽，则需要设置。
        */
        .table {
            table-layout: fixed;
        }

        /* 表格复选框单元格居中显示 */
        .table .td-checkbox {
            text-align: center;
        }

        .table .td-checkbox input {
            margin: 0;
        }

        /* 表格操作栏按钮居中 */
        .table .td-operation {
            text-align: center;
        }

        /* 表格操作栏按钮间隔 */
        .table .td-operation .btn + .btn {
            margin-bottom: 0;
            /*margin-left: 5px;*/
        }


        .info-block {
            margin: 0 0 10px 0;
        }

    </style>

    <link rel="icon" type="image/png" sizes="32x32" href="/assets/primo/images/neptune.png"/>
    <link rel="icon" type="image/png" sizes="16x16" href="/assets/primo/images/neptune.png"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="app align-content-stretch d-flex flex-wrap">
    <div class="app-sidebar">
        <div class="logo">
            <a href="index.html" class="logo-icon"><span class="logo-text">Neptune</span></a>
            <div class="sidebar-user-switcher user-activity-online">
                <a href="#">
                    <img src="/assets/primo/images/avatars/avatar.png">
                    <span class="activity-indicator"></span>
                    <span class="user-info-text">Chloe<br><span class="user-state-info">On a call</span></span>
                </a>
            </div>
        </div>
        <!--  侧边栏 开始 -->
        <div class="app-menu">
            <ul class="accordion-menu">
                <li class="sidebar-title">
                    Apps
                </li>
                <li class="active-page">
                    <a href="/index"><i class="material-icons-two-tone">dashboard</i>首页统计</a>
                </li>
                <li>
                    <a href="#"><i class="material-icons-two-tone">grid_on</i>表数据操作<i class="material-icons has-sub-menu">keyboard_arrow_right</i></a>
                    <ul class="sub-menu">
                                                    <li>
                                <a href="/primo/urlruleprocessor311config12/tables">url_rule_processor_311_config_12</a>
                            </li>
                                                     <li>
                                <a href="/primo/urlruleprocessorconfig/tables">url_rule_processor_config</a>
                            </li>
                                                     <li>
                                <a href="/primo/urlrulewebdriverconfig/tables">url_rule_web_driver_config</a>
                            </li>
                                             </ul>
                </li>
            </ul>
        </div>
    </div>

    <!--    顶部栏 开始-->
    <div class="app-container">
        <div class="search">
            <form>
                <input class="form-control" type="text" placeholder="Type here..." aria-label="Search">
            </form>
            <a href="#" class="toggle-search"><i class="material-icons">close</i></a>
        </div>
        <div class="app-header">
            <nav class="navbar navbar-light navbar-expand-lg">
                <div class="container-fluid">
                    <div class="navbar-nav" id="navbarNav">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link hide-sidebar-toggle-button" href="#"><i class="material-icons">first_page</i></a>
                            </li>
                            <li class="nav-item dropdown hidden-on-mobile">
                                <a class="nav-link dropdown-toggle" href="#" id="addDropdownLink" role="button"
                                   data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="material-icons">add</i>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="addDropdownLink">
                                    <li><a class="dropdown-item" href="#">New Workspace</a></li>
                                    <li><a class="dropdown-item" href="#">New Board</a></li>
                                    <li><a class="dropdown-item" href="#">Create Project</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown hidden-on-mobile">
                                <a class="nav-link dropdown-toggle" href="#" id="exploreDropdownLink" role="button"
                                   data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="material-icons-outlined">explore</i>
                                </a>
                                <ul class="dropdown-menu dropdown-lg large-items-menu"
                                    aria-labelledby="exploreDropdownLink">
                                    <li>
                                        <h6 class="dropdown-header">Repositories</h6>
                                    </li>
                                    <li>
                                        <a class="dropdown-item" href="#">
                                            <h5 class="dropdown-item-title">
                                                Neptune iOS
                                                <span class="badge badge-warning">1.0.2</span>
                                                <span class="hidden-helper-text">switch<i class="material-icons">keyboard_arrow_right</i></span>
                                            </h5>
                                            <span class="dropdown-item-description">Lorem Ipsum is simply dummy text of the printing and typesetting industry.</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a class="dropdown-item" href="#">
                                            <h5 class="dropdown-item-title">
                                                Neptune Android
                                                <span class="badge badge-info">dev</span>
                                                <span class="hidden-helper-text">switch<i class="material-icons">keyboard_arrow_right</i></span>
                                            </h5>
                                            <span class="dropdown-item-description">Lorem Ipsum is simply dummy text of the printing and typesetting industry.</span>
                                        </a>
                                    </li>
                                    <li class="dropdown-btn-item d-grid">
                                        <button class="btn btn-primary">Create new repository</button>
                                    </li>
                                </ul>
                            </li>
                        </ul>

                    </div>
                    <div class="d-flex">
                        <ul class="navbar-nav">
                            <li class="nav-item hidden-on-mobile">
                                <a class="nav-link active" href="#">Applications</a>
                            </li>
                            <li class="nav-item hidden-on-mobile">
                                <a class="nav-link" href="#">Reports</a>
                            </li>
                            <li class="nav-item hidden-on-mobile">
                                <a class="nav-link" href="#">Projects</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link toggle-search" href="#"><i class="material-icons">search</i></a>
                            </li>
                            <li class="nav-item hidden-on-mobile">
                                <a class="nav-link language-dropdown-toggle" href="#" id="languageDropDown"
                                   data-bs-toggle="dropdown"><img src="/assets/primo/images/flags/us.png" alt=""></a>
                                <ul class="dropdown-menu dropdown-menu-end language-dropdown"
                                    aria-labelledby="languageDropDown">
                                    <li><a class="dropdown-item" href="#"><img
                                                    src="/assets/primo/images/flags/germany.png" alt="">German</a></li>
                                    <li><a class="dropdown-item" href="#"><img src="/assets/primo/images/flags/italy.png"
                                                                               alt="">Italian</a></li>
                                    <li><a class="dropdown-item" href="#"><img src="/assets/primo/images/flags/china.png"
                                                                               alt="">Chinese</a></li>
                                </ul>
                            </li>
                            <li class="nav-item hidden-on-mobile">
                                <a class="nav-link nav-notifications-toggle" id="notificationsDropDown" href="#"
                                   data-bs-toggle="dropdown">4</a>
                                <div class="dropdown-menu dropdown-menu-end notifications-dropdown"
                                     aria-labelledby="notificationsDropDown">
                                    <h6 class="dropdown-header">Notifications</h6>
                                    <div class="notifications-dropdown-list">
                                        <a href="#">
                                            <div class="notifications-dropdown-item">
                                                <div class="notifications-dropdown-item-image">
                                                        <span class="notifications-badge bg-info text-white">
                                                            <i class="material-icons-outlined">campaign</i>
                                                        </span>
                                                </div>
                                                <div class="notifications-dropdown-item-text">
                                                    <p class="bold-notifications-text">Donec tempus nisi sed erat
                                                        vestibulum, eu suscipit ex laoreet</p>
                                                    <small>19:00</small>
                                                </div>
                                            </div>
                                        </a>
                                        <a href="#">
                                            <div class="notifications-dropdown-item">
                                                <div class="notifications-dropdown-item-image">
                                                        <span class="notifications-badge bg-danger text-white">
                                                            <i class="material-icons-outlined">bolt</i>
                                                        </span>
                                                </div>
                                                <div class="notifications-dropdown-item-text">
                                                    <p class="bold-notifications-text">Quisque ligula dui, tincidunt nec
                                                        pharetra eu, fringilla quis mauris</p>
                                                    <small>18:00</small>
                                                </div>
                                            </div>
                                        </a>
                                        <a href="#">
                                            <div class="notifications-dropdown-item">
                                                <div class="notifications-dropdown-item-image">
                                                        <span class="notifications-badge bg-success text-white">
                                                            <i class="material-icons-outlined">alternate_email</i>
                                                        </span>
                                                </div>
                                                <div class="notifications-dropdown-item-text">
                                                    <p>Nulla id libero mattis justo euismod congue in et metus</p>
                                                    <small>yesterday</small>
                                                </div>
                                            </div>
                                        </a>
                                        <a href="#">
                                            <div class="notifications-dropdown-item">
                                                <div class="notifications-dropdown-item-image">
                                                        <span class="notifications-badge">
                                                            <img src="/assets/primo/images/avatars/avatar.png" alt="">
                                                        </span>
                                                </div>
                                                <div class="notifications-dropdown-item-text">
                                                    <p>Praesent sodales lobortis velit ac pellentesque</p>
                                                    <small>yesterday</small>
                                                </div>
                                            </div>
                                        </a>
                                        <a href="#">
                                            <div class="notifications-dropdown-item">
                                                <div class="notifications-dropdown-item-image">
                                                        <span class="notifications-badge">
                                                            <img src="/assets/primo/images/avatars/avatar.png" alt="">
                                                        </span>
                                                </div>
                                                <div class="notifications-dropdown-item-text">
                                                    <p>Praesent lacinia ante eget tristique mattis. Nam sollicitudin
                                                        velit sit amet auctor porta</p>
                                                    <small>yesterday</small>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
        <div class="app-content">
            <div class="content-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col">
                            <div class="page-description">
                                <h1>数据统计</h1>
                                <span>第三方平台、网站等</span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col">
                            <p>
                                <button class="btn btn-primary" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#add-data" aria-expanded="false"
                                        aria-controls="collapseExample">
                                    添加数据
                                </button>
                                <button class="btn btn-primary" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#view-data" aria-expanded="false"
                                        aria-controls="collapseExample">
                                    查看数据
                                </button>
                                <button class="btn btn-primary" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#edit-data" aria-expanded="false"
                                        aria-controls="collapseExample">
                                    修改数据
                                </button>
                            </p>
                            <div class="collapse" id="add-data">
                                <div class="card card-body">
                                    <form id="form-add">
                                                                                                                                                         <#--                                                不是主键-->
                                                <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="platformNameEn-add">
                                                    <label for="platformNameEn-add" class="form-label" title="平台名称-英文名称">platformNameEn</label>
                                                </div>
                                                                                                                       <#--                                                不是主键-->
                                                <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="referer-add">
                                                    <label for="referer-add" class="form-label" title="访问时，来源链接">referer</label>
                                                </div>
                                                                                                                       <#--                                                不是主键-->
                                                <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="urlRuleJson-add">
                                                    <label for="urlRuleJson-add" class="form-label" title="抓取URL的规则">urlRuleJson</label>
                                                </div>
                                                                                                                       <#--                                                不是主键-->
                                                <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="titleRuleJson-add">
                                                    <label for="titleRuleJson-add" class="form-label" title="抓取标题的规则">titleRuleJson</label>
                                                </div>
                                                                                                                       <#--                                                不是主键-->
                                                <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="pulishTimeRuleJson-add">
                                                    <label for="pulishTimeRuleJson-add" class="form-label" title="发表时间的规则">pulishTimeRuleJson</label>
                                                </div>
                                                                                                                       <#--                                                不是主键-->
                                                <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="authorRuleJson-add">
                                                    <label for="authorRuleJson-add" class="form-label" title="作者的规则">authorRuleJson</label>
                                                </div>
                                                                                                                       <#--                                                不是主键-->
                                                <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="tagsRuleJson-add">
                                                    <label for="tagsRuleJson-add" class="form-label" title="标签的规则">tagsRuleJson</label>
                                                </div>
                                                                                                                       <#--                                                不是主键-->
                                                <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="dateDelete-add">
                                                    <label for="dateDelete-add" class="form-label" title="删除时间，0未删除">dateDelete</label>
                                                </div>
                                                                                                                       <#--                                                不是主键-->
                                                <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="domain-add">
                                                    <label for="domain-add" class="form-label" title="域名">domain</label>
                                                </div>
                                                                                                                       <#--                                                不是主键-->
                                                <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="blogsAuthorIndexUrlType-add">
                                                    <label for="blogsAuthorIndexUrlType-add" class="form-label" title="对应表中的type数值">blogsAuthorIndexUrlType</label>
                                                </div>
                                                                                                                       <#--                                                不是主键-->
                                                <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="createTime-add">
                                                    <label for="createTime-add" class="form-label" title="">createTime</label>
                                                </div>
                                                                                                                       <#--                                                不是主键-->
                                                <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="updateTime-add">
                                                    <label for="updateTime-add" class="form-label" title="">updateTime</label>
                                                </div>
                                                                                                                       <#--                                                不是主键-->
                                                <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="status-add">
                                                    <label for="status-add" class="form-label" title="状态，0-正常，1-禁用">status</label>
                                                </div>
                                                                                                                       <button type="button" class="btn btn-success" id="btn-add">
                                            添加
                                        </button>
                                    </form>
                                </div>
                            </div>

                            <div class="collapse" id="view-data">
                                <div class="card card-body">
                                    <form>
                                                                                <div class="mb-3 mt-3">
                                                <label for="id-view" class="form-label" title="">id - ：</label>
                                                <div id="id-view"></div>
                                            </div>
                                                                                 <div class="mb-3 mt-3">
                                                <label for="platformNameEn-view" class="form-label" title="平台名称-英文名称">platformNameEn - 平台名称-英文名称：</label>
                                                <div id="platformNameEn-view"></div>
                                            </div>
                                                                                 <div class="mb-3 mt-3">
                                                <label for="referer-view" class="form-label" title="访问时，来源链接">referer - 访问时，来源链接：</label>
                                                <div id="referer-view"></div>
                                            </div>
                                                                                 <div class="mb-3 mt-3">
                                                <label for="urlRuleJson-view" class="form-label" title="抓取URL的规则">urlRuleJson - 抓取URL的规则：</label>
                                                <div id="urlRuleJson-view"></div>
                                            </div>
                                                                                 <div class="mb-3 mt-3">
                                                <label for="titleRuleJson-view" class="form-label" title="抓取标题的规则">titleRuleJson - 抓取标题的规则：</label>
                                                <div id="titleRuleJson-view"></div>
                                            </div>
                                                                                 <div class="mb-3 mt-3">
                                                <label for="pulishTimeRuleJson-view" class="form-label" title="发表时间的规则">pulishTimeRuleJson - 发表时间的规则：</label>
                                                <div id="pulishTimeRuleJson-view"></div>
                                            </div>
                                                                                 <div class="mb-3 mt-3">
                                                <label for="authorRuleJson-view" class="form-label" title="作者的规则">authorRuleJson - 作者的规则：</label>
                                                <div id="authorRuleJson-view"></div>
                                            </div>
                                                                                 <div class="mb-3 mt-3">
                                                <label for="tagsRuleJson-view" class="form-label" title="标签的规则">tagsRuleJson - 标签的规则：</label>
                                                <div id="tagsRuleJson-view"></div>
                                            </div>
                                                                                 <div class="mb-3 mt-3">
                                                <label for="dateDelete-view" class="form-label" title="删除时间，0未删除">dateDelete - 删除时间，0未删除：</label>
                                                <div id="dateDelete-view"></div>
                                            </div>
                                                                                 <div class="mb-3 mt-3">
                                                <label for="domain-view" class="form-label" title="域名">domain - 域名：</label>
                                                <div id="domain-view"></div>
                                            </div>
                                                                                 <div class="mb-3 mt-3">
                                                <label for="blogsAuthorIndexUrlType-view" class="form-label" title="对应表中的type数值">blogsAuthorIndexUrlType - 对应表中的type数值：</label>
                                                <div id="blogsAuthorIndexUrlType-view"></div>
                                            </div>
                                                                                 <div class="mb-3 mt-3">
                                                <label for="createTime-view" class="form-label" title="">createTime - ：</label>
                                                <div id="createTime-view"></div>
                                            </div>
                                                                                 <div class="mb-3 mt-3">
                                                <label for="updateTime-view" class="form-label" title="">updateTime - ：</label>
                                                <div id="updateTime-view"></div>
                                            </div>
                                                                                 <div class="mb-3 mt-3">
                                                <label for="status-view" class="form-label" title="状态，0-正常，1-禁用">status - 状态，0-正常，1-禁用：</label>
                                                <div id="status-view"></div>
                                            </div>
                                                                         </form>
                                </div>
                            </div>

                            <div class="collapse" id="edit-data">
                                <div class="card card-body">
                                    <span>需要在表格中的编辑点击才会有数据</span>
                                    <form id="form-edit">
                                                                                                                            <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="id-edit" disabled>
                                                    <label for="id-edit" class="form-label"  title="">id</label>
                                                </div>
                                                                                                                                                                     <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="platformNameEn-edit">
                                                    <label for="platformNameEn-edit" class="form-label"  title="平台名称-英文名称">platformNameEn</label>
                                                </div>
                                                                                                                                                                     <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="referer-edit">
                                                    <label for="referer-edit" class="form-label"  title="访问时，来源链接">referer</label>
                                                </div>
                                                                                                                                                                     <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="urlRuleJson-edit">
                                                    <label for="urlRuleJson-edit" class="form-label"  title="抓取URL的规则">urlRuleJson</label>
                                                </div>
                                                                                                                                                                     <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="titleRuleJson-edit">
                                                    <label for="titleRuleJson-edit" class="form-label"  title="抓取标题的规则">titleRuleJson</label>
                                                </div>
                                                                                                                                                                     <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="pulishTimeRuleJson-edit">
                                                    <label for="pulishTimeRuleJson-edit" class="form-label"  title="发表时间的规则">pulishTimeRuleJson</label>
                                                </div>
                                                                                                                                                                     <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="authorRuleJson-edit">
                                                    <label for="authorRuleJson-edit" class="form-label"  title="作者的规则">authorRuleJson</label>
                                                </div>
                                                                                                                                                                     <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="tagsRuleJson-edit">
                                                    <label for="tagsRuleJson-edit" class="form-label"  title="标签的规则">tagsRuleJson</label>
                                                </div>
                                                                                                                                                                     <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="dateDelete-edit">
                                                    <label for="dateDelete-edit" class="form-label"  title="删除时间，0未删除">dateDelete</label>
                                                </div>
                                                                                                                                                                     <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="domain-edit">
                                                    <label for="domain-edit" class="form-label"  title="域名">domain</label>
                                                </div>
                                                                                                                                                                     <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="blogsAuthorIndexUrlType-edit">
                                                    <label for="blogsAuthorIndexUrlType-edit" class="form-label"  title="对应表中的type数值">blogsAuthorIndexUrlType</label>
                                                </div>
                                                                                                                                                                     <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="createTime-edit">
                                                    <label for="createTime-edit" class="form-label"  title="">createTime</label>
                                                </div>
                                                                                                                                                                     <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="updateTime-edit">
                                                    <label for="updateTime-edit" class="form-label"  title="">updateTime</label>
                                                </div>
                                                                                                                                                                     <div class="form-floating mb-3 mt-3">
                                                    <input type="text" class="form-control" id="status-edit">
                                                    <label for="status-edit" class="form-label"  title="状态，0-正常，1-禁用">status</label>
                                                </div>
                                                                                                                     <button type="button" class="btn btn-success" id="btn-edit">
                                            保存更改
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col">
                            <p>
                                <form class="row">
                                    <div class="btn-toolbar">
                                        <div class="col-md-3">
                                            <input type="text" class="form-control" id="fuzzy-search"
                                                   placeholder=""/>
                                        </div>
                                        <button type="button" class="btn btn-success" id="btn-simple-search" data-bs-toggle="tooltip" data-bs-placement="top" title=" platformNameEn/ referer/ domain/">模糊查询</button>
                                        <button class="btn btn-primary" type="button" data-bs-toggle="collapse"
                                                data-bs-target="#collapseExample" aria-expanded="false"
                                                aria-controls="collapseExample" id="toggle-advanced-search"
                                                title="高级查询">精准查询
                                        </button>
                                        <button type="button" class="btn btn-danger" id="btn-del">
                                            批量删除
                                        </button>
                                    </div>
                                </form>
                            </p>
                            <div class="collapse" id="collapseExample">
                                <div class="card card-body">
                                    <form>
                                                                                <div class="form-floating mb-3 mt-3">
                                                <input type="text" class="form-control" id="id-search">
                                                <label for="id-search" class="form-label" title="">id</label>
                                            </div>
                                                                                <div class="form-floating mb-3 mt-3">
                                                <input type="text" class="form-control" id="platformNameEn-search">
                                                <label for="platformNameEn-search" class="form-label" title="平台名称-英文名称">platformNameEn</label>
                                            </div>
                                                                                <div class="form-floating mb-3 mt-3">
                                                <input type="text" class="form-control" id="referer-search">
                                                <label for="referer-search" class="form-label" title="访问时，来源链接">referer</label>
                                            </div>
                                                                                <div class="form-floating mb-3 mt-3">
                                                <input type="text" class="form-control" id="urlRuleJson-search">
                                                <label for="urlRuleJson-search" class="form-label" title="抓取URL的规则">urlRuleJson</label>
                                            </div>
                                                                                <div class="form-floating mb-3 mt-3">
                                                <input type="text" class="form-control" id="titleRuleJson-search">
                                                <label for="titleRuleJson-search" class="form-label" title="抓取标题的规则">titleRuleJson</label>
                                            </div>
                                                                                <div class="form-floating mb-3 mt-3">
                                                <input type="text" class="form-control" id="pulishTimeRuleJson-search">
                                                <label for="pulishTimeRuleJson-search" class="form-label" title="发表时间的规则">pulishTimeRuleJson</label>
                                            </div>
                                                                                <div class="form-floating mb-3 mt-3">
                                                <input type="text" class="form-control" id="authorRuleJson-search">
                                                <label for="authorRuleJson-search" class="form-label" title="作者的规则">authorRuleJson</label>
                                            </div>
                                                                                <div class="form-floating mb-3 mt-3">
                                                <input type="text" class="form-control" id="tagsRuleJson-search">
                                                <label for="tagsRuleJson-search" class="form-label" title="标签的规则">tagsRuleJson</label>
                                            </div>
                                                                                <div class="form-floating mb-3 mt-3">
                                                <input type="text" class="form-control" id="dateDelete-search">
                                                <label for="dateDelete-search" class="form-label" title="删除时间，0未删除">dateDelete</label>
                                            </div>
                                                                                <div class="form-floating mb-3 mt-3">
                                                <input type="text" class="form-control" id="domain-search">
                                                <label for="domain-search" class="form-label" title="域名">domain</label>
                                            </div>
                                                                                <div class="form-floating mb-3 mt-3">
                                                <input type="text" class="form-control" id="blogsAuthorIndexUrlType-search">
                                                <label for="blogsAuthorIndexUrlType-search" class="form-label" title="对应表中的type数值">blogsAuthorIndexUrlType</label>
                                            </div>
                                                                                <div class="form-floating mb-3 mt-3">
                                                <input type="text" class="form-control" id="createTime-search">
                                                <label for="createTime-search" class="form-label" title="">createTime</label>
                                            </div>
                                                                                <div class="form-floating mb-3 mt-3">
                                                <input type="text" class="form-control" id="updateTime-search">
                                                <label for="updateTime-search" class="form-label" title="">updateTime</label>
                                            </div>
                                                                                <div class="form-floating mb-3 mt-3">
                                                <input type="text" class="form-control" id="status-search">
                                                <label for="status-search" class="form-label" title="状态，0-正常，1-禁用">status</label>
                                            </div>
                                                                            <button type="button" class="btn btn-success" id="btn-advanced-search">
                                            查询
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col">
                            <!--                            <div class="section-description section-description-inline">-->
                            <!--                                <h1>平台数据</h1>-->
                            <!--                            </div> -->
                            <div class="card">
                                <!--                                table-responsive 表格响应式 -->
                                <div class="card-body table-responsive" id="div-table-container">

                                    <table class="table table-striped table-bordered table-hover table-condensed"
                                           id="table-user">
                                        <thead>
                                        <tr>
                                            <th>
                                                <input type="checkbox" name="cb-check-all">
                                            </th>
                                                                                    <th title="">id</th>
                                                                                    <th title="平台名称-英文名称">platformNameEn</th>
                                                                                    <th title="访问时，来源链接">referer</th>
                                                                                    <th title="抓取URL的规则">urlRuleJson</th>
                                                                                    <th title="抓取标题的规则">titleRuleJson</th>
                                                                                    <th title="发表时间的规则">pulishTimeRuleJson</th>
                                                                                    <th title="作者的规则">authorRuleJson</th>
                                                                                    <th title="标签的规则">tagsRuleJson</th>
                                                                                    <th title="删除时间，0未删除">dateDelete</th>
                                                                                    <th title="域名">domain</th>
                                                                                    <th title="对应表中的type数值">blogsAuthorIndexUrlType</th>
                                                                                    <th title="">createTime</th>
                                                                                    <th title="">updateTime</th>
                                                                                    <th title="状态，0-正常，1-禁用">status</th>
                                                                                <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <th>
                                                <input type="checkbox" name="cb-check-all">
                                            </th>
                                                                                <#--                                               TODO  时间需要转换-->
                                                <th title="">id</th>
                                                                                <#--                                               TODO  时间需要转换-->
                                                <th title="平台名称-英文名称">platformNameEn</th>
                                                                                <#--                                               TODO  时间需要转换-->
                                                <th title="访问时，来源链接">referer</th>
                                                                                <#--                                               TODO  时间需要转换-->
                                                <th title="抓取URL的规则">urlRuleJson</th>
                                                                                <#--                                               TODO  时间需要转换-->
                                                <th title="抓取标题的规则">titleRuleJson</th>
                                                                                <#--                                               TODO  时间需要转换-->
                                                <th title="发表时间的规则">pulishTimeRuleJson</th>
                                                                                <#--                                               TODO  时间需要转换-->
                                                <th title="作者的规则">authorRuleJson</th>
                                                                                <#--                                               TODO  时间需要转换-->
                                                <th title="标签的规则">tagsRuleJson</th>
                                                                                <#--                                               TODO  时间需要转换-->
                                                <th title="删除时间，0未删除">dateDelete</th>
                                                                                <#--                                               TODO  时间需要转换-->
                                                <th title="域名">domain</th>
                                                                                <#--                                               TODO  时间需要转换-->
                                                <th title="对应表中的type数值">blogsAuthorIndexUrlType</th>
                                                                                <#--                                               TODO  时间需要转换-->
                                                <th title="">createTime</th>
                                                                                <#--                                               TODO  时间需要转换-->
                                                <th title="">updateTime</th>
                                                                                <#--                                               TODO  时间需要转换-->
                                                <th title="状态，0-正常，1-禁用">status</th>
                                                                                <th>操作</th>
                                        </tr>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<!-- Javascripts -->
<script src="/assets/primo/plugins/jquery/jquery-3.5.1.min.js"></script>
<script src="/assets/primo/plugins/bootstrap/js/popper.min.js"></script>
<script src="/assets/primo/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="/assets/primo/plugins/perfectscroll/perfect-scrollbar.min.js"></script>
<script src="/assets/primo/plugins/pace/pace.min.js"></script>
<script src="/assets/primo/plugins/highlight/highlight.pack.js"></script>
<script src="/assets/primo/plugins/datatables/datatables.min.js"></script>
<script src="/assets/primo/js/main.min.js"></script>
<script src="/assets/primo/js/base.js"></script>
<script>
    /*常量*/
    var CONSTANT = {
        DATA_TABLES: {
            DEFAULT_OPTION: { //DataTables初始化选项
                language: {
                    "sProcessing": "处理中...",
                    "sLengthMenu": "每页 _MENU_ 项",
                    "sZeroRecords": "没有匹配结果",
                    "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
                    "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
                    "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                    "sInfoPostFix": "",
                    "sSearch": "搜索:",
                    "sUrl": "",
                    "sEmptyTable": "表中数据为空",
                    "sLoadingRecords": "载入中...",
                    "sInfoThousands": ",",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "上页",
                        "sNext": "下页",
                        "sLast": "末页",
                        "sJump": "跳转"
                    },
                    "oAria": {
                        "sSortAscending": ": 以升序排列此列",
                        "sSortDescending": ": 以降序排列此列"
                    }
                },
                autoWidth: true,	//禁用自动调整列宽
                stripeClasses: ["odd", "even"],//为奇偶行加上样式，兼容不支持CSS伪类的场合
                order: [],			//取消默认排序查询,否则复选框一列会出现小箭头
                processing: true,	//加载提示,自行处理
                serverSide: true,	//启用服务器端分页
                searching: false,	//禁用原生搜索

                pagingType: "full_numbers",//分页样式 numbers - 只有只有数字按钮
                // simple - 只有上一页、下一页两个按钮
                // simple_numbers - 除了上一页、下一页两个按钮还有页数按钮，Datatables默认是这个
                // full - 有四个按钮首页、上一页、下一页、末页
                // full_numbers - 除首页、上一页、下一页、末页四个按钮还有页数按钮
                // first_last_numbers - 除首页、末页两个按钮还有页数按钮
                aLengthMenu: [15, 20, 50, 100], //设置每页显示记录的下拉菜单
                pageLength: 15,//分页大小
                deferRender: true,//当处理大数据时，延迟渲染数据，有效提高Datatables处理能力
            },
            COLUMN: {
                CHECKBOX: {	//复选框单元格
                    className: "td-checkbox",
                    orderable: false,
                    width: "30px",
                    data: null,
                    render: function (data, type, row, meta) {
                        return '<input type="checkbox" class="iCheck">';
                    }
                }
            },
            // 超过显示省略号
            RENDER: {	//常用render可以抽取出来，如日期时间、头像等
                ELLIPSIS: function (data, type, row, meta) {
                    data = data || "";
                    return '<span class="ellipsis_line" title="' + data + '">' + data + '</span>';
                }
            }
        }
    };
    var tableUser = $('#table-user');
    $(function () {
        var wrapper = $('#div-table-container');
        var _table = tableUser.dataTable($.extend(true, {}, CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
            ajax: function (data, callback, settings) {//ajax配置为function,手动调用异步查询
                //封装请求参数
                var param = userManage.getQueryCondition(data);
                getTableData(param,callback)
            },
            //有业务数据的计算。 简单宽度计算。3个英文算一个汉字。默认120px，小于120个英文。大于600个英文进行省略展示（使用300px展示）。120英文-600英文使用200px展示
            //根据数据库字段长度的计算：16字符内，默认80px，32字符内，默认120px，32-128字符，默认160px，128-512字符，默认200px。超过512-使用300px，且使用省略
            columns: [
                CONSTANT.DATA_TABLES.COLUMN.CHECKBOX

                        ,
                {
                    className: "wordwrap",
                                        width: "80px",
                                        data: "id"
                }
                        ,
                {
                    className: "wordwrap",
                                        width: "120px",
                                        data: "platformNameEn"
                }
                        ,
                {
                    className: "wordwrap",
                                        width: "160px",
                                        data: "referer"
                }
                        ,
                {
                    className: "wordwrap",
                                        width: "300px",
                    //切记设置table样式为table-layout:fixed; 否则列宽不会强制为指定宽度，也不会出现省略号。
                    render: CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,
                                        data: "urlRuleJson"
                }
                        ,
                {
                    className: "wordwrap",
                                        width: "300px",
                    //切记设置table样式为table-layout:fixed; 否则列宽不会强制为指定宽度，也不会出现省略号。
                    render: CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,
                                        data: "titleRuleJson"
                }
                        ,
                {
                    className: "wordwrap",
                                        width: "300px",
                    //切记设置table样式为table-layout:fixed; 否则列宽不会强制为指定宽度，也不会出现省略号。
                    render: CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,
                                        data: "pulishTimeRuleJson"
                }
                        ,
                {
                    className: "wordwrap",
                                        width: "300px",
                    //切记设置table样式为table-layout:fixed; 否则列宽不会强制为指定宽度，也不会出现省略号。
                    render: CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,
                                        data: "authorRuleJson"
                }
                        ,
                {
                    className: "wordwrap",
                                        width: "300px",
                    //切记设置table样式为table-layout:fixed; 否则列宽不会强制为指定宽度，也不会出现省略号。
                    render: CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,
                                        data: "tagsRuleJson"
                }
                        ,
                {
                    className: "wordwrap",
                                        width: "80px",
                                        data: "dateDelete"
                }
                        ,
                {
                    className: "wordwrap",
                                        width: "160px",
                                        data: "domain"
                }
                        ,
                {
                    className: "wordwrap",
                                        width: "80px",
                                        data: "blogsAuthorIndexUrlType"
                }
                        ,
                {
                    className: "wordwrap",
                                        width: "120px",
                                        data: "createTime"
                }
                        ,
                {
                    className: "wordwrap",
                                        width: "120px",
                                        data: "updateTime"
                }
                        ,
                {
                    className: "wordwrap",
                                        width: "80px",
                                        data: "status"
                }
                        ,
                {
                    className: "td-operation",
                    data: null,
                    defaultContent: "",
                    orderable: false,
                    width: "120px"
                }
            ],
            "createdRow": function (row, data, index) {
                //行渲染回调,在这里可以对该行dom元素进行任何操作
                //给当前行加样式
                if (data.role) {
                    $(row).addClass("info");
                }
                //给当前行某列加样式
                // $('td', row).eq(3).addClass(data.status ? "text-success" : "text-error");
                //不使用render，改用jquery文档操作呈现单元格。增加操作
                var btnEdit = $('<button type="button" class="btn btn-primary btn-style-light btn-small btn-edit">修改</button>');
                var btnDel = $('<button type="button" class="btn btn-danger btn-style-light btn-small btn-del">删除</button>');
                //操作
                $('td', row).eq(15).append(btnEdit).append(btnDel);
            },
            "drawCallback": function (settings) {
                //渲染完毕后的回调
                //清空全选状态
                $(":checkbox[name='cb-check-all']", wrapper).prop('checked', false);
                //默认选中第一行 - 会有第一行点击的监听 ,不进行选中,避免数据为空
                $("tbody tr", tableUser).eq(0).click();
            }
        })).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象

        $("#btn-add").click(function () {
            userManage.addItemInit();
        });

        $("#btn-del").click(function () {
            var arrItemId = [];
            $("tbody :checkbox:checked", tableUser).each(function (i) {
                var item = _table.row($(this).closest('tr')).data();
                arrItemId.push(item);
            });
            userManage.deleteItem(arrItemId);
        });

        // 外边的查询按钮
        $("#btn-simple-search").click(function () {
            userManage.fuzzySearch = true;
            //reload效果与draw(true)或者draw()类似,draw(false)则可在获取新数据的同时停留在当前页码,可自行试验
//		_table.ajax.reload();
//		_table.draw(false);
            _table.draw();
        });

        $("#btn-advanced-search").click(function () {
            // 模糊查询开关/
            userManage.fuzzySearch = false;
            _table.draw();
        });

        $("#btn-add").click(function () {
            userManage.addItemSubmit();
        });
        $("#btn-edit").click(function () {
            userManage.editItemSubmit();
        });

        //行点击事件-行选中监听
        $("tbody", tableUser).on("click", "tr", function (event) {
            $(this).addClass("active").siblings().removeClass("active");
            //获取该行对应的数据
            var item = _table.row($(this).closest('tr')).data();
            userManage.currentItem = item;
            // 显示明细
            console.log("点击行事件，item=" + JSON.stringify(item))
            userManage.showItemDetail(item);
            //编辑数据
            userManage.editItemInit(item);
        });

        // 表格中的数据监听
        tableUser.on("change", ":checkbox", function () {
            if ($(this).is("[name='cb-check-all']")) {
                //全选
                $(":checkbox", tableUser).prop("checked", $(this).prop("checked"));
            } else {
                //一般复选
                var checkbox = $("tbody :checkbox", tableUser);
                $(":checkbox[name='cb-check-all']", tableUser).prop('checked', checkbox.length === checkbox.filter(':checked').length);
            }
        }).on("click", ".td-checkbox", function (event) {
            //点击单元格即点击复选框
            !$(event.target).is(":checkbox") && $(":checkbox", this).trigger("click");
        }).on("click", ".btn-edit", function () {
            var item = _table.row($(this).closest('tr')).data();
            $(this).closest('tr').addClass("active").siblings().removeClass("active");
            userManage.currentItem = item;
            //点击编辑按钮
            console.log("点击修改按钮,item=" + JSON.stringify(item))
            userManage.editItemInit(item);
        }).on("click", ".btn-del", function () {
            //点击删除按钮
            var item = _table.row($(this).closest('tr')).data();
            $(this).closest('tr').addClass("active").siblings().removeClass("active");
            userManage.deleteItem([item]);
        });

        $("#toggle-advanced-search").click(function () {
            $("i", this).toggleClass("fa-angle-double-down fa-angle-double-up");
            $("#div-advanced-search").slideToggle("fast");
        });

        $("#btn-info-content-collapse").click(function () {
            $("i", this).toggleClass("fa-minus fa-plus");
            $("span", this).toggle();
            $("#user-view .info-content").slideToggle("fast");
        });

        $(".btn-cancel").click(function () {
            userManage.showItemDetail(userManage.currentItem);
        });
    });

    /**
     * 缓存上一个的请求数据
     */
    var lastTablePostParam;
    var lastTableCallback;
    /**
     * 获取表格数据
     * @param data
     */
    function getTableData(param,callback){
        lastTablePostParam = param;
        lastTableCallback = callback;
        $.ajax({
            type: "POST",
            url: "/primo/urlrulewebdriverconfig/page",
            cache: false,	//禁用缓存
            data: param,	//传入已封装的参数
            dataType: "json",
            //RequestBody 传参
            contentType:'application/json;charset=UTF-8',
            success: function (result) {
                // result = JSON.parse(result);
                console.log("请求的返回数据：result.message=" + JSON.stringify(result.message))
                //setTimeout仅为测试遮罩效果。 延迟展示
                // setTimeout(function () {
                // }, 2000);
                //异常判断与处理
                if (result.code !== 200) {
                    confirmReminder("数据查询","查询失败。错误码：" + result.code + "，异常信息：" + result.message);
                    return;
                }
                //封装返回数据，这里仅演示了修改属性名
                var returnData = {};
                returnData.draw = result.data.draw;//这里直接自行返回了draw计数器,应该由后台返回。前端传，后端回填
                //总数
                returnData.recordsTotal = result.data.total;
                returnData.recordsFiltered = result.data.total;
                //记录数
                returnData.data = result.data.records;
                //关闭遮罩
                // wrapper.spinModal(false);
                //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                callback(returnData);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                // $.dialog.alert("查询失败");
                // wrapper.spinModal(false);
                confirmReminder("数据查询","查询失败");
            }
        });
    }

    var userManage = {
        currentItem: null,
        fuzzySearch: true,
        // 封装请求的参数
        getQueryCondition: function (data) {
            var param = {
                "queryBo": {
                    "queryBoFuzzyList":[],
                    "queryBoExt":{}
                }
            };
            //组装排序参数
            if (data.order && data.order.length && data.order[0]) {
                //默认正序
                param.queryBo.queryBoExt.asc = true;
                switch (data.order[0].column) {

                            case 1:
                        param.queryBo.queryBoExt.orderColumn = "id";
                        break;
                            case 2:
                        param.queryBo.queryBoExt.orderColumn = "platform_name_en";
                        break;
                            case 3:
                        param.queryBo.queryBoExt.orderColumn = "referer";
                        break;
                            case 4:
                        param.queryBo.queryBoExt.orderColumn = "url_rule_json";
                        break;
                            case 5:
                        param.queryBo.queryBoExt.orderColumn = "title_rule_json";
                        break;
                            case 6:
                        param.queryBo.queryBoExt.orderColumn = "pulish_time_rule_json";
                        break;
                            case 7:
                        param.queryBo.queryBoExt.orderColumn = "author_rule_json";
                        break;
                            case 8:
                        param.queryBo.queryBoExt.orderColumn = "tags_rule_json";
                        break;
                            case 9:
                        param.queryBo.queryBoExt.orderColumn = "date_delete";
                        break;
                            case 10:
                        param.queryBo.queryBoExt.orderColumn = "domain";
                        break;
                            case 11:
                        param.queryBo.queryBoExt.orderColumn = "blogs_author_index_url_type";
                        break;
                            case 12:
                        param.queryBo.queryBoExt.orderColumn = "create_time";
                        break;
                            case 13:
                        param.queryBo.queryBoExt.orderColumn = "update_time";
                        break;
                            case 14:
                        param.queryBo.queryBoExt.orderColumn = "status";
                        break;
                            default:
                        //这块默认的搜索需要处理 - 默认id
                        param.queryBo.queryBoExt.orderColumn = "id";
                        break;
                }
                if(data.order[0].dir==='asc'){
                    param.queryBo.queryBoExt.asc = true;
                }else if((data.order[0].dir==='desc')){
                    param.queryBo.queryBoExt.asc = false;
                }
                console.log("排序输出 param.queryBo.queryBoExt："+JSON.stringify(param.queryBo.queryBoExt))
            }
            //组装查询参数
            // param.fuzzySearch = userManage.fuzzySearch;
            if (userManage.fuzzySearch) {
                // 模糊查询的属性
                var value =  $("#fuzzy-search").val();
                if(value!==''){
                                param.queryBo.queryBoFuzzyList[0] = {};
                    param.queryBo.queryBoFuzzyList[0].column = "platform_name_en";
                    param.queryBo.queryBoFuzzyList[0].value = value;
                                param.queryBo.queryBoFuzzyList[1] = {};
                    param.queryBo.queryBoFuzzyList[1].column = "referer";
                    param.queryBo.queryBoFuzzyList[1].value = value;
                                param.queryBo.queryBoFuzzyList[2] = {};
                    param.queryBo.queryBoFuzzyList[2].column = "domain";
                    param.queryBo.queryBoFuzzyList[2].value = value;
                        }
            } else {
                //遍历,精准查询
                        var idValue = $("#id-search").val();
                if(idValue!=='') {
                    param.queryBo.id = idValue;
                }
                        var platformNameEnValue = $("#platformNameEn-search").val();
                if(platformNameEnValue!=='') {
                    param.queryBo.platformNameEn = platformNameEnValue;
                }
                        var refererValue = $("#referer-search").val();
                if(refererValue!=='') {
                    param.queryBo.referer = refererValue;
                }
                        var urlRuleJsonValue = $("#urlRuleJson-search").val();
                if(urlRuleJsonValue!=='') {
                    param.queryBo.urlRuleJson = urlRuleJsonValue;
                }
                        var titleRuleJsonValue = $("#titleRuleJson-search").val();
                if(titleRuleJsonValue!=='') {
                    param.queryBo.titleRuleJson = titleRuleJsonValue;
                }
                        var pulishTimeRuleJsonValue = $("#pulishTimeRuleJson-search").val();
                if(pulishTimeRuleJsonValue!=='') {
                    param.queryBo.pulishTimeRuleJson = pulishTimeRuleJsonValue;
                }
                        var authorRuleJsonValue = $("#authorRuleJson-search").val();
                if(authorRuleJsonValue!=='') {
                    param.queryBo.authorRuleJson = authorRuleJsonValue;
                }
                        var tagsRuleJsonValue = $("#tagsRuleJson-search").val();
                if(tagsRuleJsonValue!=='') {
                    param.queryBo.tagsRuleJson = tagsRuleJsonValue;
                }
                        var dateDeleteValue = $("#dateDelete-search").val();
                if(dateDeleteValue!=='') {
                    param.queryBo.dateDelete = dateDeleteValue;
                }
                        var domainValue = $("#domain-search").val();
                if(domainValue!=='') {
                    param.queryBo.domain = domainValue;
                }
                        var blogsAuthorIndexUrlTypeValue = $("#blogsAuthorIndexUrlType-search").val();
                if(blogsAuthorIndexUrlTypeValue!=='') {
                    param.queryBo.blogsAuthorIndexUrlType = blogsAuthorIndexUrlTypeValue;
                }
                        var createTimeValue = $("#createTime-search").val();
                if(createTimeValue!=='') {
                    param.queryBo.createTime = createTimeValue;
                }
                        var updateTimeValue = $("#updateTime-search").val();
                if(updateTimeValue!=='') {
                    param.queryBo.updateTime = updateTimeValue;
                }
                        var statusValue = $("#status-search").val();
                if(statusValue!=='') {
                    param.queryBo.status = statusValue;
                }
                    }
            //组装分页参数，开始页数 //开始的记录序号
            // param.startIndex = data.start;
            //当前页码
            param.pageNo = (data.start / data.length) + 1;
            //每页数量
            param.pageSize = data.length;
            //前端请求时带draw参数，返回时后端一并返回，防止ajax异步导致数据返回不对应的问题。
            param.draw = data.draw;
            // param.queryBo = JSON.stringify(param.queryBo);
            console.log("查询数据，接口请求参数：" + JSON.stringify(param));
            return JSON.stringify(param);
        },
        // 显示具体行的数据
        showItemDetail: function (item) {
            if(item===null || item === undefined){
                return;
            }
                    $("#id-view").text(item.id);
                    $("#platformNameEn-view").text(item.platformNameEn);
                    $("#referer-view").text(item.referer);
                    $("#urlRuleJson-view").text(item.urlRuleJson);
                    $("#titleRuleJson-view").text(item.titleRuleJson);
                    $("#pulishTimeRuleJson-view").text(item.pulishTimeRuleJson);
                    $("#authorRuleJson-view").text(item.authorRuleJson);
                    $("#tagsRuleJson-view").text(item.tagsRuleJson);
                    $("#dateDelete-view").text(item.dateDelete);
                    $("#domain-view").text(item.domain);
                    $("#blogsAuthorIndexUrlType-view").text(item.blogsAuthorIndexUrlType);
                    $("#createTime-view").text(item.createTime);
                    $("#updateTime-view").text(item.updateTime);
                    $("#status-view").text(item.status);
                },
        //添加数据
        addItemInit: function () {
            // var classValue = $("#add-data").attr("class");
            // if(classValue.indexOf("show") === -1){
            //     $("#add-data").addClass("show");
            // }
            // //移除另外两个的展示
            // $("#edit-data").removeClass("show");
            // $("#view-data").removeClass("show");
        },
        // 编辑数据
        editItemInit: function (item) {
            $("#form-edit")[0].reset();
            if(item==null){
                return;
            }
                    $("#id-edit").val(item.id);
                    $("#platformNameEn-edit").val(item.platformNameEn);
                    $("#referer-edit").val(item.referer);
                    $("#urlRuleJson-edit").val(item.urlRuleJson);
                    $("#titleRuleJson-edit").val(item.titleRuleJson);
                    $("#pulishTimeRuleJson-edit").val(item.pulishTimeRuleJson);
                    $("#authorRuleJson-edit").val(item.authorRuleJson);
                    $("#tagsRuleJson-edit").val(item.tagsRuleJson);
                    $("#dateDelete-edit").val(item.dateDelete);
                    $("#domain-edit").val(item.domain);
                    $("#blogsAuthorIndexUrlType-edit").val(item.blogsAuthorIndexUrlType);
                    $("#createTime-edit").val(item.createTime);
                    $("#updateTime-edit").val(item.updateTime);
                    $("#status-edit").val(item.status);
                },
        //发起添加数据
        addItemSubmit: function () {
            //获取参数
            var param = {};
                                                    // 非主键
            param.platformNameEn =  $("#platformNameEn-add").val();
                                            // 非主键
            param.referer =  $("#referer-add").val();
                                            // 非主键
            param.urlRuleJson =  $("#urlRuleJson-add").val();
                                            // 非主键
            param.titleRuleJson =  $("#titleRuleJson-add").val();
                                            // 非主键
            param.pulishTimeRuleJson =  $("#pulishTimeRuleJson-add").val();
                                            // 非主键
            param.authorRuleJson =  $("#authorRuleJson-add").val();
                                            // 非主键
            param.tagsRuleJson =  $("#tagsRuleJson-add").val();
                                            // 非主键
            param.dateDelete =  $("#dateDelete-add").val();
                                            // 非主键
            param.domain =  $("#domain-add").val();
                                            // 非主键
            param.blogsAuthorIndexUrlType =  $("#blogsAuthorIndexUrlType-add").val();
                                            // 非主键
            param.createTime =  $("#createTime-add").val();
                                            // 非主键
            param.updateTime =  $("#updateTime-add").val();
                                            // 非主键
            param.status =  $("#status-add").val();
                                //发起请求
            confirm(addItem,param,"新增数据提示","确定保存当前添加数据，参数数据："+JSON.stringify(param));
        },
        //发起编辑数据
        editItemSubmit: function () {
            //获取参数
            var param = {};
                    param.id =  $("#id-edit").val();
                    param.platformNameEn =  $("#platformNameEn-edit").val();
                    param.referer =  $("#referer-edit").val();
                    param.urlRuleJson =  $("#urlRuleJson-edit").val();
                    param.titleRuleJson =  $("#titleRuleJson-edit").val();
                    param.pulishTimeRuleJson =  $("#pulishTimeRuleJson-edit").val();
                    param.authorRuleJson =  $("#authorRuleJson-edit").val();
                    param.tagsRuleJson =  $("#tagsRuleJson-edit").val();
                    param.dateDelete =  $("#dateDelete-edit").val();
                    param.domain =  $("#domain-edit").val();
                    param.blogsAuthorIndexUrlType =  $("#blogsAuthorIndexUrlType-edit").val();
                    param.createTime =  $("#createTime-edit").val();
                    param.updateTime =  $("#updateTime-edit").val();
                    param.status =  $("#status-edit").val();
                    //发起请求
            confirm(editItem,param,"修改数据提示","确定修改当前数据，参数数据："+JSON.stringify(param));
        },
        deleteItem: function (selectedItems) {
            var message;
            if (selectedItems && selectedItems.length) {
                if (selectedItems.length === 1) {
                    message = "确定要删除 '" + selectedItems[0].name + "' 吗?";

                } else {
                    message = "确定要删除选中的" + selectedItems.length + "项记录吗?";
                }
                confirm("", "", message, JSON.stringify(selectedItems));
            } else {
                confirmReminder("删除提示", "请先选中要操作的行");
            }
        }
    };
    // 添加数据
    function addItem(parameter){
        ajaxPostRequestBody("/primo/urlrulewebdriverconfig/save",JSON.stringify(parameter),resetFormData,true,$("#form-add")[0]);

    }
    function resetFormData(response,form){
        //表单重置,应该是请求成功再重置
        form.reset();
        confirmReminderTimeOut("请求提示", "请求成功。返回code：" + response.code + "，返回信息:" + response.message,2000);
        //刷新表格数据
        // tableUser.DataTable().ajax.reload();
        getTableData(lastTablePostParam,lastTableCallback);
    }
    // 添加数据
    function editItem(parameter){
        //表单重置,应该是请求成功再重置
        ajaxPostRequestBody("/primo/urlrulewebdriverconfig/update",JSON.stringify(parameter),resetFormData,true,$("#form-edit")[0]);
    }

</script>

</body>

</html>
