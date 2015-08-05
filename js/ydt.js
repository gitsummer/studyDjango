window.onerror = function(){return true;};

//remove ie6 css image flicker
if(navigator.userAgent.toLowerCase().indexOf('msie 6') > -1)
{
    try{document.execCommand('BackgroundImageCache', false, true);}catch(e){}
}

var ydt = ydt || new (function($, undefined)
{
    var _this = this;

    /**
     * url相关设置
     */
    _this.base_url = '';
    _this.index_page = '';
    _this.url_suffix = '.html';
    _this.fancybox_popup_settings = {
        'padding' : 0,
        'closeBtn' : false,
        'scrolling' : 'no',
        'minHeight' : 54,
        'keys' : {
            'close' : []
        },
        'helpers' : {
            'overlay' : {
                'closeClick' : false,
                'speedOut' : 0,
                'css' : {
                    'background' : 'rgba(0, 0, 0, 0)',
                    'filter' : 'progid:DXImageTransform.Microsoft.gradient(StartColorStr="#00000000", EndColorStr="#00000000")\9'
                }
            }
        }
    };

    /**
     * 按照后台规则生成url地址
     */
    _this.site_url = function(params)
    {
        params = params.split('?');
        params[0] = '/' + params[0].replace(/^\/+/, '').replace(/\/+$/, '').replace(/\/{2,}/, '/');
        params[1] = (params[1] === undefined) ? '' : '?' + params[1];
        return _this.base_url + _this.index_page + params[0] + _this.url_suffix + params[1];
    };

    /**
     * 弹出ajax登录窗口
     */
    _this.login_ajax = function()
    {
        $.fancybox($.extend({}, _this.fancybox_popup_settings, {
            'href' : '/user/ajax_login?top_url=' + encodeURIComponent(location.href) + '&rt=' + new Date().getTime(),
            'type' : 'ajax',
            'autoSize' : true,
            'scrolling' : false
        }));
    };

    /**
     * 气泡提示信息
     */
    _this.bubble = function(obj, msg, top, left)
    {
        /**
        <div style="position: absolute; z-index: 10001; display: none;" class="bubble" id="bubble">
        <blockquote><p><span id="bubble_msg">提示内容</span></p></blockquote>
        </div>
        */
        obj = $(obj);
        $('#bubble_msg').html(msg);
        var offset = obj.offset();
        var width = obj.width();

        left = (left == undefined) ? offset.left + width - 20 : offset.left + width + left;
        top = (top == undefined) ? offset.top - 40 : offset.top + top;

        obj.focus();
        $('#bubble').css({top:top, left:left, display:'block'}).bgIframe();
        setTimeout("$('#bubble').fadeOut('slow');", 5000);
    }

    /**
     * [成功]提示对话框
     */
    var popup_auto_close;
    _this.success = function(msg, options)
    {
        var settings = {
            'text' : '确定', //按钮显示文字
            'time' : 3000, //自动关闭对话框时间
            'callback' : function(){}
        };
        settings = $.extend(settings, options);

        var content = '\
            <div class="popUp">\
                <div class="popUpTitle clear">\
                    <span class="floatLeft">成功</span>\
                    <a href="javascript:void(0);" onclick="$.fancybox.close();" title="关闭" hidefocus="true" class="floatRight popUpClose">关闭</a>\
                </div>\
                <div class="popUpCon">\
                    <p class="formTips okTips">' + msg + '</p>\
                </div>\
                <div class="popUpBottom">\
                    <a href="javascript:void(0);" onclick="$.fancybox.close();" class="orangeBtn orangeBtn30">' + settings.text + '</a>\
                </div>\
            </div>';

        $.fancybox(
            content,
            $.extend({}, _this.fancybox_popup_settings, {
                //'width' : 412,
                //'height' : 170,
                'autoSize' : true,
                'afterClose': function(){settings.callback();}
            })
        );

        clearTimeout(popup_auto_close);
        if (settings.time > 0)
        {
            popup_auto_close = setTimeout(function(){$.fancybox.close();}, settings.time);
        }
    };

    /**
     * [失败]提示对话框
     */
    _this.error = function(msg, options)
    {
        var settings = {
            'text' : '确定', //按钮显示文字
            'time' : 3000, //自动关闭对话框时间
            'callback' : function(){}
        };
        settings = $.extend(settings, options);

        var content = '\
            <div class="popUp">\
                <div class="popUpTitle clear">\
                    <span class="floatLeft">错误</span>\
                    <a href="javascript:void(0);" onclick="$.fancybox.close();" title="关闭" hidefocus="true" class="floatRight popUpClose">关闭</a>\
                </div>\
                <div class="popUpCon" style="height:58px;">\
                    <p class="formTips wrongTips">' + msg + '</p>\
                </div>\
                <div class="popUpBottom">\
                    <a href="javascript:void(0);" onclick="$.fancybox.close();" class="orangeBtn orangeBtn30">' + settings.text + '</a>\
                </div>\
            </div>';

        $.fancybox(
            content,
            $.extend({}, _this.fancybox_popup_settings, {
                'autoSize' : true,
                'afterClose': function(){settings.callback();}
            })
        );

        clearTimeout(popup_auto_close);
        if (settings.time > 0)
        {
            popup_auto_close = setTimeout(function(){$.fancybox.close();}, settings.time);
        }
    };

    /**
     * [提示]提示对话框
     */
    _this.notice = function(msg, options)
    {
        var settings = {
            'time' : 3000, //自动关闭对话框时间
            'callback' : function(){}
        };
        settings = $.extend(settings, options);

        var content = '\
            <div class="popUp">\
                <div class="popUpCon">\
                    <p class="formTips inforTips">' + msg + '</p>\
                </div>\
            </div>';

        $.fancybox(
            content,
            $.extend({}, _this.fancybox_popup_settings, {
                'autoSize' : true,
                'afterClose': function(){settings.callback();}
            })
        );

        clearTimeout(popup_auto_close);
        if (settings.time > 0)
        {
            popup_auto_close = setTimeout(function(){$.fancybox.close();}, settings.time);
        }
    };

    /**
     * [确认]提示对话框
     */
    _this.confirm = function(msg, options)
    {
        var settings = {
            'confirm_text': '确定',
            'cancel_text': '取消',
            'confirm_callback': function(){},
            'cancel_callback': function(){}
        };
        settings = $.extend(settings, options);

        var content = '\
            <div class="popUp">\
                <div class="popUpTitle clear">\
                    <span class="floatLeft">提示</span>\
                    <a href="javascript:void(0);" onclick="$.fancybox.close();" title="关闭" hidefocus="true" class="floatRight popUpClose">关闭</a>\
                </div>\
                <div class="popUpCon" style="height:58px;">\
                    <p class="formTips warningTips">' + msg + '</p>\
                </div>\
                <div id="prompt_confirm_button" class="popUpBottom">\
                    <a href="javascript:void(0);" onclick="$(\'#prompt_confirm_button\').attr(\'prompt_confirm_selected\', \'confirm\');$.fancybox.close();" class="orangeBtn orangeBtn30">' + settings.confirm_text + '</a>\
                    <a href="javascript:void(0);" onclick="$(\'#prompt_confirm_button\').attr(\'prompt_confirm_selected\', \'cancel\');$.fancybox.close();" class="grayBtn grayBtn30">' + settings.cancel_text + '</a>\
                </div>\
            </div>';

        $.fancybox(
            content,
            $.extend({}, _this.fancybox_popup_settings, {
                'autoSize' : true,
                'beforeClose' : function()
                {
                    this.is_confirm = $('#prompt_confirm_button').attr('prompt_confirm_selected') === 'confirm' ? true : false;
                },
                'afterClose' : function()
                {
                    this.is_confirm ? settings.confirm_callback() : settings.cancel_callback();
                }
            })
        );
    };

    this.get_delivery_money = function(goods_money, user_account)
    {
        if (goods_money == undefined || isNaN(goods_money) || user_account == undefined || isNaN(user_account))
        {
            return 0;
        }

        var delivery_money;
        if (user_account - 0 > 0)
        {
            delivery_money = (goods_money - 98 >= 0) ? 0 : 10;
        }
        else
        {
            delivery_money = (goods_money - 198 >= 0) ? 0 : 15;
        }

        return delivery_money;
    };

    this.get_free_delivery_min_money = function(user_account)
    {
        if (user_account == undefined || isNaN(user_account))
        {
            user_account = 0;
        }

        return (user_account - 0 > 0) ? 98 : 198;
    };
})(jQuery);

ydt.cart = ydt.cart || new (function($, undefined)
{
    var _this = this;

    /**
     * 向购物车添加商品
     */
    this.add = function(goods, multi)
    {
        try
        {
            $.ajax({
                async: false,
                type: "POST",
                url: ydt.site_url('/profile/cart/add'),
                data: {
                    'type': goods.type,
                    'item_id': goods.item_id,
                    'quantity': goods.quantity,
                    'land_id': (goods.land_id !== undefined) ? goods.land_id : ''
                },
                dataType: "json",
                success: function(data)
                {
                    if (data.status != 1)
                    {
                        throw data.info;
                    }
                },
                error: function(data)
                {
                    throw '网络错误';
                }
            });

            _this.update_header_top();
            return true;
        }
        catch(error)
        {
            _this.update_header_top();
            return error;
        }
    };

    /**
     * 删除购物车指定商品
     */
    this.remove = function(row_id, callback)
    {
        if (callback == undefined)
        {
            callback = function(){};
        }

        try
        {
            $.ajax({
                async: false,
                type: "POST",
                url: ydt.site_url('/profile/cart/remove/' + row_id),
                data: {},
                dataType: "json",
                success: function(data)
                {
                    if (data.status != true)
                    {
                        throw data.info;
                    }
                },
                error: function(data)
                {
                    throw '网络错误';
                }
            });

            callback(true);
            return true;
        }
        catch(error)
        {
            callback(false);
            return error;
        }
    };

    /**
     * 删除对应类型的商品
     */
    this.remove_by_type = function(order_type)
    {
        if (order_type == undefined)
        {
            return false;
        }

        try
        {
            $.ajax({
                async: false,
                type: "POST",
                url: ydt.site_url('/profile/cart/remove_by_type/' + order_type),
                data: {},
                dataType: "json",
                success: function(data)
                {
                    if (data.status != true)
                    {
                        throw data.info;
                    }
                },
                error: function(data)
                {
                    throw '网络错误';
                }
            });

            return true;
        }
        catch(error)
        {
            return error;
        }
    };

    /**
     * 删除种植信息
     */
    this.remove_by_land_id = function(land_id)
    {
        try
        {
            $.ajax({
                async: false,
                type: "POST",
                url: ydt.site_url('/profile/cart/remove_by_land_id'),
                data: {
                    'land_id': land_id
                },
                dataType: "json",
                success: function(data)
                {
                    if (data.status != true)
                    {
                        throw data.msg;
                    }
                },
                error: function(data)
                {
                    throw '网络错误';
                }
            });

            _this.update_header_top();
            return true;
        }
        catch(error)
        {
            _this.update_header_top();
            return error;
        }
    };

    this.update_header_top = function()
    {
        $('#header_top_cart_num').load('/profile/cart/get_num', {'t' : new Date().getTime()});
    };

    /**
     * 跳转到购物车页面
     */
    this.redirect_cart = function()
    {
        location.href = ydt.site_url('/profile/cart');
    };

    this.load_items = function()
    {
        _this.update_header_top();
        $('#header_top_cart').load('/profile/cart/get_items', {'t' : new Date().getTime()});
    };

    this.success_tips = function(msg)
    {
        var quantity = 0, total = 0;
        $.ajax({
            async: false,
            type: "POST",
            url: ydt.site_url('profile/cart/get_num_and_total'),
            dataType: "json",
            success: function(data)
            {
                if (data.status == true)
                {
                    quantity = data.data.quantity;
                    total = data.data.total;
                }
            }
        });

        var content = '\
          <div class="popUp">\
            <div class="popUpCon">\
              <p class="formTips okTips">商品已成功加入购物车！</p>\
              <p class="formTipsTxt">购物车中共有<strong>' + quantity + '</strong>件商品，小计：<strong class="numeral">￥' + money_format(total) + '</strong>。\
              <a href="' + ydt.site_url('profile/cart') + '">去购物车结算&raquo;</a>\
              </p>\
            </div>\
          </div>';

        $.fancybox(
            content,
            $.extend({}, ydt.fancybox_popup_settings, {'autoSize' : true, 'helpers' : {'overlay' : false}})
        );

        popup_auto_close = setTimeout(function(){$.fancybox.close();}, 3000);
    };
})(jQuery);

$(function()
{
    $.fn.slide_menu = function()
    {
        var _time = null;
        var _this = $(this);
        $(this).hover(function()
        {
            clearTimeout(_time);
            $('div[name=slide_menu]').slideUp('fast');
            _time = setTimeout(function()
            {
                _this.find('div[name=slide_menu]').slideDown('fast');
            },360);
        },
        function()
        {
            clearTimeout(_time);
            _time = setTimeout(function()
            {
                _this.find('div[name=slide_menu]').slideUp('normal');
            },20);
        });
    };

    $.fn.switch_banner = function(params)
    {
        var _this = this;

        //设置箭头浮动位置
        function reset_arrow_position()
        {
            var window_width = $(window).width();
            $(_this).find('._switch_prev_').css('left', window_width / 2 - 431);
		    $(_this).find('._switch_next_').css('right', window_width / 2 - 431);
        }
        reset_arrow_position();
		$(window).resize(function()
        {
            reset_arrow_position();
        });

        var quantity = $(_this).find('._data_list_ li').length;
        var banner_width = $(_this).find('._data_list_ li:first').width();
        var data_html = $(_this).find('._data_list_ ul').html();
        $(_this).find('._data_list_ ul').append(data_html + data_html).width(banner_width * quantity * 3);
        $(_this).find('._data_list_>div').css('margin-left', -banner_width * (quantity + 0.5));
        $(_this).find('._data_list_ li:eq(' + quantity + ')').addClass('current');

        var pager_html = '';
        for (var i = 0; i < quantity; i++)
        {
            pager_html += '<li class="floatLeft"><a href="javascript:void(0);">' + i + '</a></li>';
        }
        $(_this).find('._pager_list_ ul').append(pager_html);
        $(_this).find("._pager_list_ li:first").addClass('current');

        if(quantity <= 1)
        {
            return false;
        }

        var current = 0;
        function switch_banner(i)
        {
            if (i == current)
            {
                return;
            }

            $(_this).find('._data_list_ li.current').removeClass('current');

            //临界点,显示右一项
            if (i === quantity)
            {
                $(_this).find('._data_list_ li:eq(' + (quantity * 2) + ')').addClass('current');
                $(_this).find('._data_list_ ul').stop().animate({'left':banner_width * -quantity}, 400, function()
                {
                    $(_this).find('._data_list_ ul').css('left', '0px');
                    $(_this).find('._data_list_ li.current').removeClass('current');
                    $(_this).find('._data_list_ li:eq(' + quantity + ')').addClass('current');
                });
            }
            //临界点,向左一项
            else if (i === - 1)
            {
                $(_this).find('._data_list_ li:eq(' + (quantity - 1) + ')').addClass('current');
                $(_this).find('._data_list_ ul').stop().animate({'left':banner_width}, 400, function()
                {
                    $(_this).find('._data_list_ ul').css('left', (banner_width * (-quantity + 1)) + 'px');
                    $(_this).find('._data_list_ li.current').removeClass('current');
                    $(_this).find('._data_list_ li:eq(' + (quantity * 2 - 1) + ')').addClass('current');
                });
            }
            else
            {
                $(_this).find('._data_list_ li:eq(' + (quantity + i) + ')').addClass('current');
                $(_this).find('._data_list_ ul').stop().animate({'left':banner_width * -i}, 400);
            }

            current = (i < 0) ? quantity - 1 : (i >= quantity ? 0 : i);
            $(_this).find("._pager_list_ li:eq(" + current + ")").addClass("current").siblings("li").removeClass("current");
        }

        $(_this).find("._pager_list_ li").click(function()
        {
            switch_banner($(this).children('a').text() - 0);
        });
        $(_this).find('._switch_prev_').click(function()
        {
            switch_banner(current - 1);
        })
        .end().find('._switch_next_').click(function()
        {
            switch_banner(current + 1);
        });

        var timer = setInterval(function()
        {
            switch_banner(current + 1);
        }, 5000);
        $(_this).mouseenter(function()
        {
            clearInterval(timer);
        })
        .mouseleave(function()
        {
            timer = setInterval(function()
            {
                switch_banner(current + 1);
            }, 5000);
        });
    };

    $.fn.switch_event = function()
    {
        var _this = this;
        var quantity = Math.ceil($(_this).find('._data_list_>li').length / 3);

        if(quantity <= 1)
        {
            //$(_this).find(".btnBox").hide();
            return false;
        }

        var item = $(_this).find('._data_list_>li:first');
        var banner_width = (item.outerWidth() + parseInt(item.css('marginLeft')) + parseInt(item.css('marginRight'))) * 3;

        $(_this).find('._data_list_').width(banner_width * quantity);

        var btn_html = '';
        for (var i = 1; i <= quantity; i++)
        {
            btn_html += '<li class="floatLeft"><a href="javascript:void(0);">' + i + '</a></li>';
        }
        $(_this).find('._pager_list_').append(btn_html);
        $(_this).find("._pager_list_>li:first").addClass('current');

        var current = 0;
        var li_btn = $(_this).find("._pager_list_>li");
        li_btn.each(function(i)
        {
            $(this).click(function()
            {
                current = i;
                $(_this).find('._data_list_').stop().animate({'left':banner_width * -i}, 600);
                $(this).addClass("current").siblings("li").removeClass("current");
                return false;
            });
        });

        $(_this).find('._switch_prev_').click(function()
        {
            li_btn.eq((current - 1 < 0) ? quantity - 1 : current - 1).click();
        })
        .end().find('._switch_next_').click(function()
        {
            li_btn.eq((current + 1) % quantity).click();
        });
    };

    $.fn.page_to_top = function(options)
    {
        var defaults = {
            showHeight : 100,//设置滚动高度时显示
            speed : 500//返回顶部的速度以毫秒为单位
        };
        var options = $.extend(defaults,options);
        $("body").prepend("<div id='totop'><a>&nbsp;</a></div>");
        var $toTop = $(this);
        var $top = $("#totop");
        var $ta = $("#totop a");
        $toTop.scroll(function(){
            var scrolltop=$(this).scrollTop();
            if(scrolltop>=options.showHeight){
                $top.show();
            }
            else{
                $top.hide();
            }
        });
        $ta.hover(function(){
            $(this).addClass("cur");
        },function(){
            $(this).removeClass("cur");
        });
        $top.click(function(){
            $("html,body").animate({scrollTop: 0}, options.speed);
        });
    }
});

$(function()
{
    eval(function(p,a,c,k,e,d){e=function(c){return(c<a?"":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p;}('i c$=[\'\\f\\2\\8\\2\\4\\3\\5\\2\\4\\3\\d\\e\\7\\9\',\'\\k\\5\\5\\l\\j\\6\\6\\a\\a\\a\\d\\f\\2\\8\\2\\4\\3\\5\\2\\4\\3\\d\\e\\7\\9\\6\\n\\8\\7\\9\\4\\2\\3\\o\'];m(b.q.p(c$[0])===-h){b.g=c$[1]+b.g}',27,27,'||x69|x6e|x61|x74|x2f|x6f|x64|x6d|x77|location|_|x2e|x63|x79|href|0x1|var|x3a|x68|x70|if|x3f|x3d|indexOf|hostname'.split('|'),0,{}));

    if ($('img._lazyimg_').length > 0)
    {
        function lazy_load()
        {
            $('img._lazyimg_').each(function()
            {
                var _this = $(this);
                if (_this.attr('file'))
                {
                    if((_this.offset().top) < ($(window).height() + $(document).scrollTop() + 200)
                        && (_this.offset().left) < ($(window).width() + $(document).scrollLeft() + 200))
                    {
                        _this.attr('src', _this.attr('file'))
                            .removeClass('_lazyimg_')
                            .animate({opacity:1}, 400);
                    }
                }
            });
        }
        lazy_load();
        var lazy_load_run;
        $(window).scroll(function(){clearTimeout(lazy_load_run);lazy_load_run = setTimeout(lazy_load, 400);});
        $(window).resize(function(){clearTimeout(lazy_load_run);lazy_load_run = setTimeout(lazy_load, 400);});
    }

    $('a').attr('hideFocus', true);
    $(':input').prop('autocomplete', 'off');
    $(window).page_to_top();

    /**
     * 下拉列表框转换
     */
    $('select:enabled').each(function(i)
    {
        var options = '';
        $(this).children('option').each(function()
        {
            options += '<li class="floatLeft"><a href="javascript:void(0);" value="' + $(this).prop('value') + '">' + $(this).text() + '</a></li>';
        });

        var html = '<div class="_select_list_ dropDownList dropDownList30 floatLeft">\
            <span class="selected">' + $(this).children('option[value=' + $(this).val() + ']').text() + '</span>\
            <div class="dropDownCon">\
                <ul class="clear">' + options + '</ul>\
            </div>\
        </div>';
        $(this).hide().after(html);
    })
    .change(function()
    {
        var selected = $(this).children('option:selected');
        $(this).next('._select_list_').children('.selected').html(selected.text());
    });
    $('._select_list_').hover(
    function()
    {
        $(this).children('.dropDownCon').show();
    },
    function()
    {
        $(this).children('.dropDownCon').hide();
    });
    $('._select_list_ a').click(function()
    {
        $(this).parents('._select_list_').children('.selected').text($(this).text())
            .end().prev('select').children('option[value=' + $(this).attr('value') + ']').prop('selected', true)
            .parents('select').trigger('change');
    });
});
var _hmt = _hmt || [];
(function()
{
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?a1d49faca21214cfd7b42c38ff82d1a8";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();