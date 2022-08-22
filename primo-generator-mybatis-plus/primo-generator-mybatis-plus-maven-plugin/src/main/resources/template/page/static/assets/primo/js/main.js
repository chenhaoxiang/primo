$(document).ready(function () {

    "use strict";
    //更多精品模板：http://www.bootstrapmb.com
    // Properties
    const submenu_animation_speed = 200;

    // Functions
    const appMenu = function () {

        if ($('.horizontal-menu').length) {

            $('.hide-sidebar-toggle-button').on('click', function (e) {
                e.preventDefault()
                toggleSidebar()
            });

            var select_sub_menus = $('.app-menu li:not(.open) ul'),
            active_page_sub_menu_link = $('.app-menu li.active-page > a');

            var ps;

            if ($(window).width() > 1199) {
                if (ps != null) {
                    ps.destroy();
                    ps = null;
                }
            } else {
                var container = document.querySelector('.app-menu');
                ps = new PerfectScrollbar(container);
                
                // Hide all sub-menus
                select_sub_menus.hide();
            }

            $(window).resize(function () {
                if ($(window).width() > 1199 && ps != null) {
                    ps.destroy();
                    ps = null;
                } else {
                    var container = document.querySelector('.app-menu');
                    ps = new PerfectScrollbar(container);
                
                    // Hide all sub-menus
                    select_sub_menus.hide();
                }
            });

            $('.app-menu li a').on('click', function (e) {


                var sub_menu = $(this).next('ul'),
                    parent_list_el = $(this).parent('li'),
                    active_list_element = $('.app-menu .menu-list > li.open'),
                    show_sub_menu = function () {
                        sub_menu.slideDown(submenu_animation_speed);
                        parent_list_el.addClass('open');
                        ps.update();
                    },
                    hide_sub_menu = function () {
                        sub_menu.slideUp(submenu_animation_speed);
                        parent_list_el.removeClass('open');
                        ps.update();
                    },
                    hide_active_menu = function () {
                        parent_list_el.parent().children('.open').children('ul').slideUp(submenu_animation_speed);
                        parent_list_el.parent().children('.open').removeClass('open');
                        ps.update();
                    };

                if (sub_menu.length) {

                    if ($(window).width() > 1199) {
                        e.preventDefault();
                        return;
                    }

                    if (!parent_list_el.hasClass('open')) {
                        if (active_list_element.length) {
                            hide_active_menu();
                        };
                        show_sub_menu();
                    } else {
                        hide_sub_menu();
                    };

                    return false;

                };


            });
        }

        if (!$('.app-sidebar').length) {
            return;
        }

        var select_sub_menus = $('.accordion-menu li:not(.open) ul'),
            active_page_sub_menu_link = $('.accordion-menu li.active-page > a');



        // Hide all sub-menus
        select_sub_menus.hide();

        var ps;

        if ($(".app.menu-hover").length && $(window).width() > 1199) {
            ps.destroy();
            ps = null;
        } else {
            var container = document.querySelector('.app-menu');
            ps = new PerfectScrollbar(container);
        }

        $(window).resize(function() {
            if ($(".app.menu-hover").length && $(window).width() > 1199 && !ps.length) {
                var container = document.querySelector('.app-menu');
                ps = new PerfectScrollbar(container);
            } else if (ps.length) {
                ps.destroy();
                ps = null;
            }
        });


        // Menu
        $('.accordion-menu li a').on('click', function (e) {


            var sub_menu = $(this).next('ul'),
                parent_list_el = $(this).parent('li'),
                active_list_element = $('.accordion-menu > li.open'),
                show_sub_menu = function () {
                    sub_menu.slideDown(submenu_animation_speed);
                    parent_list_el.addClass('open');
                    ps.update();
                },
                hide_sub_menu = function () {
                    sub_menu.slideUp(submenu_animation_speed);
                    parent_list_el.removeClass('open');
                    ps.update();
                },
                hide_active_menu = function () {
                    parent_list_el.parent().children('.open').children('ul').slideUp(submenu_animation_speed);
                    parent_list_el.parent().children('.open').removeClass('open');
                    ps.update();
                };

            if (sub_menu.length) {

                if ($('.app').hasClass('menu-hover') && $(window).width() > 1199) {
                    e.preventDefault();
                    return;
                }

                if (!parent_list_el.hasClass('open')) {
                    if (active_list_element.length) {
                        hide_active_menu();
                    };
                    show_sub_menu();
                } else {
                    hide_sub_menu();
                };

                return false;

            };


        });

        if (($('.active-page > ul').length)) {
            if(!($('.app').hasClass('menu-hover'))) {
                active_page_sub_menu_link.click();
            } else if ($(window).width() < 1199) {
                active_page_sub_menu_link.click();
            }
        };

        if (!$('.app').hasClass('menu-off-canvas')) {
            if ($(window).width() < 1199 && !$('.app').hasClass('sidebar-hidden')) {
                if(!$('.hide-app-sidebar-mobile').length) {
                    $('.app').append('<div class="hide-app-sidebar-mobile"></div>'); 
                }
                $('.hide-sidebar-toggle-button i').text('last_page');
            } else {
                $('.hide-sidebar-toggle-button i').text('first_page');
            }

            $( window ).resize(function() {
                if ($(window).width() < 1199 && !$('.app').hasClass('sidebar-hidden')) {
                    if(!$('.hide-app-sidebar-mobile').length) {
                        $('.app').append('<div class="hide-app-sidebar-mobile"></div>'); 
                    }
                    $('.hide-sidebar-toggle-button i').text('last_page');
                } else {
                    $('.hide-sidebar-toggle-button i').text('first_page');
                }
            });
        }

        $('.hide-sidebar-toggle-button').on('click', function (e) {
            e.preventDefault()
            toggleSidebar()
        });

        $('.hide-app-sidebar-mobile').on('click', function (e) {
            e.preventDefault()
            toggleSidebar()
        });

        function toggleSidebar() {
            if ($('.app').hasClass('menu-off-canvas')) {
                return false;
            }
            $('.app').toggleClass('sidebar-hidden');
            if ($('.app').hasClass('sidebar-hidden')) {
                setTimeout(function () {
                    $('.app-sidebar .logo').addClass('hidden-sidebar-logo');
                }, 200)
                if ($(window).width() > 1199) {
                    $('.hide-sidebar-toggle-button i').text('last_page');
                } else {
                    $('.hide-sidebar-toggle-button i').text('first_page');
                }
            } else {
                $('.app-sidebar .logo').removeClass('hidden-sidebar-logo');

                if ($(window).width() > 1199) {
                    $('.hide-sidebar-toggle-button i').text('first_page');
                } else {
                    $('.hide-sidebar-toggle-button i').text('last_page');
                }
            }
            return false;
        };


        $('.menu-off-canvas .hide-sidebar-toggle-button').on('click', function () {
            $('.app').toggleClass('menu-off-canvas-show');
            if ($('.app').hasClass('menu-off-canvas-show')) {
                    $('.app-sidebar .logo').addClass('canvas-sidebar-hidden-logo');
            } else {
                setTimeout(function () {
                    $('.app-sidebar .logo').removeClass('canvas-sidebar-hidden-logo');
                }, 200)
            }
            return false;
        });

    };

    $('.toggle-search').on('click', function (e) {
        $('.app').toggleClass('search-visible')
        e.preventDefault()
    });

    // Plugins
    const plugins = function () {

        $('[data-bs-toggle="popover"]').popover();
        $('[data-bs-toggle="tooltip"]').tooltip();

        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);

    };

    $('.content-menu-toggle').on('click', function() {
        $('body').toggleClass('content-menu-shown')
    })

    const components = () => {
        if ($('.content-menu').length) {
            const container = document.querySelector('.content-menu');
            const ps = new PerfectScrollbar(container);

        }

    }

    // Init Functions
    appMenu();
    plugins();
    components();
    if (typeof hljs != "undefined") {
        hljs.initHighlighting();
    }
});

$(window).on("load", function () {
    setTimeout(function() {
    $('body').addClass('no-loader')}, 1000)
});