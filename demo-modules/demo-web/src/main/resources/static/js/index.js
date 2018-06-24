var main = {
    URL: {
        showIndex: function () {
            return '/index'
        },
        clear: function () {
            return '/login/clear';
        },
        cartItem: function (id, count) {
            return '/cart/' + id + '/' + count + '/cartItem';
        },
        delItem: function (id) {
            return '/cart/' + id + '/delete';
        },
        checkOrder: function () {
            return '/cart/confirmOrder/check';
        },
        showOrder: function (id) {
            return '/order/' + id + '/show';
        },
        loginCheckURL: function () {
            return "/login/check";
        },
        regCheckUrl: function (name) {
            return "/register/" + name + "/check";
        },
        registerUrl: function () {
            return "/register"
        },
        submitOrder: function () {
            return "/cart/submitOrder";
        },
        orderCenter: function (uid) {
            return "/order/" + uid + '/orderCenter'
        },
        changePassword: function () {
            return "/changePassword"

        },

        productNameCheck: function () {
            return '/admin/productAdmin/productNameCheck';
        },
        productEditor: function () {
            return '/admin/productAdmin/productEditor';
        },
        productDel: function (id) {
            return '/admin/productAdmin/' + id + '/delete';
        }

    },

    page: {
        head: function (user) {
            var orderCenter = $("#orderCenter");
            var admin = $("#admin");
            var changePassword = $("#changePassword");
            var indexLog = $("#indexLog");
            var indexReg = $("#indexReg");
            var hello = $("#hello");
            var signOut = $("#signOut");

            indexLog.click(function () {
                main.sign.showLog();
            });
            indexReg.click(function () {
                main.sign.showReg();
            });
            signOut.click(function () {
                signOut.attr("disabled", "disabled");
                $.post(main.URL.clear(), {}, function () {
                    signOut.removeAttr("disabled");
                    window.location.assign(main.URL.showIndex());
                });
            });
            changePassword.click(function () {

                main.sign.showChangePassword();
            });
            main.sign.sign();
            main.sign.login();
            main.sign.register();
            main.sign.changePassword(user);
            if (user != null) {
                var name = user.name;
                var autho = user.autho;
                var id = user.id;
                if (autho === 0) {
                    admin.show();
                }
                indexLog.hide();
                indexReg.hide();
                orderCenter.show();
                changePassword.show();
                hello.show();
                signOut.show();
                hello.html("hi," + name);
                orderCenter.click(function () {
                    window.location.assign(main.URL.orderCenter(id));
                });
            }

        },
        index: function () {
            $(".teaID").each(function (index) {
                var tidId = "tid" + index;
                var decId = "dec" + index;
                var countId = "count" + index;
                var addId = "add" + index;
                var stocksId = "stocks" + index;
                var cartId = "cart" + index;
                var cart = $("#" + cartId);
                var tid = $("#" + tidId).val();
                var stocks = $("#" + stocksId);
                var dec = $("#" + decId);
                var add = $("#" + addId);
                var count = $("#" + countId);
                var tStocks = stocks.html();
                if (tStocks <= 0) {
                    stocks.text("缺货");
                    stocks.addClass("text-danger");
                    cart.attr("disabled", "disabled");
                }
                dec.click(function () {
                    var tCount = count.val();
                    if (tCount > 1) {
                        tCount--;
                    } else {
                        tCount = 1;
                    }
                    count.val(tCount);

                });
                add.click(function () {
                    var tCount = count.val();
                    tCount++;
                    count.val(tCount);
                });
                count.change(function () {
                    var tCount = count.val();
                    if (tCount <= 0 || isNaN(tCount)) {
                        main.page.showDialog("数量必须为大于0的整数");
                        count.val(1);
                    }//只判断值是否相等
                    else if (parseInt(tCount,10)!=tCount) {
                        main.page.showDialog("数量必须为整数");
                        count.val(parseInt(tCount,10));
                    }
                });
                cart.click(function () {
                    cart.attr("disabled", "disabled");
                    var myModal = $("#myModal-1");
                    var tCount = count.val();
                    $.post(main.URL.cartItem(tid, tCount), {}, function (result) {
                        var message = result['stateInfo'];
                        myModal.on('hide.bs.modal', function () {
                            cart.removeAttr("disabled", "disabled");
                        });
                        main.page.showDialog(message);
                        if (!result['result']) {
                            myModal.on('hide.bs.modal', function () {
                                window.location.reload();
                            })
                        }

                    });

                });
            });

        },

        showDialog: function (message) {
            var myModal = $("#myModal-1");
            var modalMessage = $("#message-1");
            modalMessage.html(message);
            myModal.modal("show");
        },
        showDialogCantDrop: function (message) {
            var myModal = $("#myModal-1");
            var modalMessage = $("#message-1");
            modalMessage.html(message);
            myModal.modal({
                show: true,
                backdrop: 'static',
                keyboard: false
            });
        }

    },
    sign: {
        showSign: function () {
            if ($("#login").hasClass('active')) {
                $("#formLog").show();
                $("#formReg").hide();
            }
            if ($("#register").hasClass('active')) {
                $("#formReg").show();
                $("#formLog").hide();
            }
        },
        sign: function () {
            $("#login").click(function () {
                $("#formLog").show();
                $("#formReg").hide();
            });
            $("#register").click(function () {
                $("#formReg").show();
                $("#formLog").hide();
            });

        },
        login: function () {
            var log = $("#log");
            log.click(function () {
                log.attr("disabled", "disabled");
                if (main.sign.loginCheck()) {
                    var data = $("#formLog").serialize();
                    $.post(main.URL.loginCheckURL(), data, function (result) {
                        log.removeAttr("disabled");
                        if (result['result']) {
                            window.location.reload();
                        } else {
                            $("#logMessage").html(result['stateInfo'])
                        }
                    });
                }
                log.removeAttr("disabled");
            });
        },
        loginCheck: function () {
            var name = $("#logName").val();
            var password = $("#logPassword").val();
            var logMessage = $("#logMessage");
            if (name.length === 0) {
                logMessage.html("用户名不能为空");
                return false;
            } else if (password.length === 0) {
                logMessage.html("密码不能为空");
                return false;
            } else {
                logMessage.html("");
                return true;
            }
        },
        register: function () {
            var reg = $("#reg");
            reg.click(function () {
                reg.attr("disabled", "disabled");
                var userNameMessage = $("#userNameMessage");
                var passwordMessage = $("#passwordMessage");
                var data = $("#formReg").serialize();
                if (userNameMessage.html() === "账号可以使用" && passwordMessage.html().length === 0) {
                    $.post(main.URL.registerUrl(), data, function () {
                        reg.removeAttr("disabled");
                        window.location.reload();
                    });
                }
                reg.removeAttr("disabled");
            });
        },
        showLog: function () {
            var sign = $("#myModal-2");
            $("#login").addClass("active");
            $("#register").removeClass('active');
            main.sign.showSign();
            sign.modal("show");

        },
        showReg: function () {
            var sign = $("#myModal-2");
            $("#login").removeClass("active");
            $("#register").addClass('active');
            main.sign.showSign();
            sign.modal("show");
        },
        regUserNameCheck: function () {
            var name = $("#regName").val();
            var userNameMessage = $("#userNameMessage");
            if (name.length === 0) {
                userNameMessage.html("账号不能为空！");
            } else {
                $.post(main.URL.regCheckUrl(name), {}, function (result) {
                    userNameMessage.html(result['stateInfo']);
                });
                userNameMessage.html("");
            }

        },
        passwordCheck: function () {
            var password = $("#regPassword").val();
            var passwordMessage = $("#passwordMessage");
            if (password.length === 0) {
                passwordMessage.html("密码不能为空！");
            } else {
                passwordMessage.html("");
            }
        },
        regPasswordCheck: function () {
            var password1 = $("#regPassword").val();
            var password2 = $("#regPassword2").val();
            var passwordMessage = $("#passwordMessage");
            if (password1 !== password2) {
                passwordMessage.html("两次输入密码不同!");
            } else {
                passwordMessage.html("");
            }
        },
        showChangePassword: function () {
            var changePassword = $("#myModal-3");
            changePassword.modal("show");
        },
        oldePasswordCheck: function () {
            var oldePasswordMessage = $("#oldePasswordMessage");
            var oldePassword = $("#oldePassword");
            if (oldePassword.val().length === 0) {
                oldePasswordMessage.html("密码不能为空")
            } else {
                oldePasswordMessage.html("");
            }

        },
        newPassword1Check: function () {
            var newPassword1Message = $("#newPassword1Message");
            var newPassword1 = $("#newPassword1");
            if (newPassword1.val().length === 0) {
                newPassword1Message.html("密码不能为空")
            } else {
                newPassword1Message.html("");
            }
        },
        newPassword2Check: function () {
            var newPassword2Message = $("#newPassword2Message");
            var newPassword2 = $("#newPassword2");
            var newPassword1 = $("#newPassword1");
            if (newPassword1.val() !== newPassword2.val()) {
                newPassword2Message.html("两次输入密码不一致")
            } else {
                newPassword2Message.html("");
            }

        },
        changePasswordCheck: function () {
            main.sign.oldePasswordCheck();
            main.sign.newPassword1Check();
            main.sign.newPassword2Check();
            var oldePasswordMessage = $("#oldePasswordMessage").html();
            var newPassword1Message = $("#newPassword1Message").html();
            var newPassword2Message = $("#newPassword2Message").html();
            if (oldePasswordMessage.length !== 0 || newPassword1Message.length !== 0 || newPassword2Message.length !== 0) {
                return false;
            } else {
                return true;
            }
        },
        changePassword: function (user) {

            var confirm = $("#confirm");
            confirm.click(function () {
                confirm.attr("disabled", "disabled");
                var password = user.password;
                var oldePassword = $("#oldePassword").val();
                var newPassword = $("#newPassword1").val();
                var oldePasswordMessage = $("#oldePasswordMessage");
                if (main.sign.changePasswordCheck()) {
                    if (oldePassword !== password) {
                        oldePasswordMessage.html("原密码错误")
                        confirm.removeAttr("disabled");
                    } else {
                        user.newPassword = newPassword;
                        $.post(main.URL.changePassword(),
                            user,
                            function (result) {
                                main.page.showDialogCantDrop(result['stateInfo']);
                                if (result['result']) {
                                    $('#myModal-1').on('hide.bs.modal', function () {
                                        confirm.removeAttr("disabled");
                                        window.location.reload();
                                    })
                                }
                            });
                    }
                }
                confirm.removeAttr("disabled");
            });

        }
    },
    cart: {
        showCart: function () {
            var allTotal = $("#allTotal");
            var sum1 = 0;
            $(".cartTid").each(function (index) {
                var count = $("#cartCount" + index);
                var price = $("#cartPrice" + index);
                var total = $("#cartTotal" + index);
                var add = $("#cartAdd" + index);
                var dec = $("#cartDec" + index);
                var del = $("#cartDel" + index);
                var tid = $("#cartTid" + index);
                sum1 += Number(total.html());
                add.click(function () {
                    var sum = Number(allTotal.html());
                    var value = count.val();
                    value++;
                    count.val(value);
                    var text = price.html() * value;
                    total.html(text);
                    sum += Number(price.html());
                    allTotal.html(sum);
                    $("#totalMoney").val(sum);
                });
                dec.click(function () {
                    var sum = Number(allTotal.html());
                    var value = count.val();
                    value--;
                    if (value < 1) {
                        value = 1;
                    } else {
                        sum -= Number(price.html());
                    }
                    count.val(value);
                    var text = price.html() * value;
                    total.html(text);
                    allTotal.html(sum);
                    $("#totalMoney").val(sum);
                });
                del.click(function () {
                    del.attr("disabled", "disabled");
                    var id = tid.val();
                    var url = main.URL.delItem(id);
                    if (confirm("确认删除？")) {
                        $.post(
                            url,
                            {},
                            function () {
                                window.location.reload();
                            }
                        );
                    }
                    del.removeAttr("disabled");
                });
                count.change(function () {
                    var value = $(this).val();
                    var checkValue = parseInt(value, 10);
                    if (value <= 0 || isNaN(value)) {
                        main.page.showDialog("数量必须为大于0的整数");
                        count.val(1);
                        calculate(1);
                    //只判断值是否相等
                    }else if (checkValue!=value) {
                        main.page.showDialog("数量必须整数");
                        count.val(checkValue);
                        calculate(checkValue);
                    } else {
                        calculate(value);
                    }
                });
                function calculate(value) {
                    var text = price.html() * value;
                    total.html(text);
                    var sum = 0;
                    $(".total").each(function () {
                        var tSum = $(this).html();
                        sum += Number(tSum);
                    });
                    allTotal.html(sum);
                    $("#totalMoney").val(sum);
                }
            });
            allTotal.html(sum1);
            $("#totalMoney").val(sum1);
        },
        submitOrder: function () {
            var money = $("#totalMoney").val();
            var submitOrder = $("#submitOrder");
            submitOrder.attr("disabled", "disabled");
            if (money == null || money === '0') {
                $("#myModal-1").on('hide.bs.modal', function () {
                    submitOrder.removeAttr("disabled");
                });
                main.page.showDialog("您还没购买东西，请先购买");
                return false;
            } else {
                submitOrder.removeAttr("disabled");
                return true;
            }
        },
        confirmOrder: function (user) {
            var confirmOrder = $("#confirmOrder");
            confirmOrder.click(function () {
                confirmOrder.attr("disabled", "disabled");
                if (user == null) {
                    $("#myModal-2").on('hide.bs.modal', function () {
                        confirmOrder.removeAttr("disabled");
                    });
                    main.sign.showLog();
                } else {
                    var myModal = $('#myModal-1');
                    $.post(main.URL.checkOrder(), {}, function (result) {
                        var message = result['stateInfo'];
                        myModal.on('hide.bs.modal', function () {
                            confirmOrder.removeAttr("disabled");
                        });
                        main.page.showDialog(message);
                        if (result['stateInfo'] === '登录超时' || result['stateInfo'] === '数据失效') {
                            myModal.on('hide.bs.modal', function () {
                                window.location.reload();
                            })
                        }
                        //如果下单成功，跳转至提示下单成功页，并且几秒后跳转或返回商家，暂时没做跳转页面，
                        //直接跳转订单中心了
                        if (result['result']) {
                            // var id = result['data'].id;
                            myModal.on('hide.bs.modal', function () {
                                window.location.assign(main.URL.orderCenter(user.id));
                            })
                        }
                    });
                }
            });
        }
    },
    order:{
        orderCenter:function () {
            $(".orderId").each(function (index) {
                var orderIdInput = $("#orderId" + index);
                var orderDetails = $("#orderDetails" + index);
                orderDetails.click(function () {
                    var orderId = orderIdInput.val();
                    window.location.assign(main.URL.showOrder(orderId));
                })
            })
        }

    },
    admin: {
        productNameCheck: function () {
            var editorMessage = $("#editorMessage");
            var ProductNameInput = $("#productName");
            var productName = ProductNameInput.val();
            var productId = $("#productId").val();
            if (productName.length === 0) {
                editorMessage.html("错误提示：商品名不能为空");
                return false;
            } else {
                editorMessage.html("");
                var data = {"productName": productName, "productId": productId};
                $.post(main.URL.productNameCheck(), data, function (result) {
                    if (!result['result']) {
                        editorMessage.html(result['stateInfo']);
                    }
                });
                return editorMessage.html().length === 0;
            }
        },
        productPriceCheck: function () {
            var editorMessage = $("#editorMessage");
            var productPrice = $("#productPrice");
            var price = productPrice.val();
            if (price.length === 0) {
                editorMessage.html("错误提示：价格不能为空");
                return false;
            } else if (price <= 0 || isNaN(price)) {
                editorMessage.html("错误提示：价格必须为大于0的数字");
                return false;
            } else {
                editorMessage.html("");
                return true;
            }
        },
        productStocksCheck: function () {
            var editorMessage = $("#editorMessage");
            var productStocks = $("#productStocks");
            var stocks = productStocks.val();
            if (stocks.length === 0) {
                editorMessage.html("错误提示：库存不能为空");
                return false;
            } else if (isNaN(stocks)) {
                editorMessage.html("错误提示：更新库存必须为数字(增加为正数，减少为负数)");
                return false;
            } else {
                editorMessage.html("");
                return true;
            }
        },
        productAllCheck: function () {
            var editorMessage = $("#editorMessage");
            var productId = $("#productId");
            var productImg = $("#productImg");
            if (productId.val().length === 0) {
                if (productImg.val().length === 0) {
                    editorMessage.html("错误提示：图标未选择");
                    return false;
                } else {
                    editorMessage.html("");
                    if (!main.admin.productNameCheck()) {
                        return false;
                    }
                    if (!main.admin.productPriceCheck()) {
                        return false;
                    }
                    return main.admin.productStocksCheck();

                }
            } else {
                if (!main.admin.productNameCheck()) {
                    return false;
                }
                if (!main.admin.productPriceCheck()) {
                    return false;
                }
                return main.admin.productStocksCheck();

            }
        },
        adminProduct: function () {
            main.admin.showAdmin();
            var submitProduct = $("#submitProduct");
            var productEditorFrom = $("#productEditorFrom");
            var productIdInput = $("#productId");
            // var productImg = $("#productImg");
            $("#myModal-4").on('hide.bs.modal', function () {
                productEditorFrom[0].reset();
            });
            submitProduct.one("click", function () {
                if (main.admin.productAllCheck()) {
                    //JQ选择器获取的都是数组。
                    var data = new FormData(productEditorFrom[0]);
                    //异步上传文件，
                    $.ajax({
                        type: 'POST',
                        url: main.URL.productEditor(),
                        dataType: "json",
                        data: data,
                        processData: false,
                        contentType: false,
                        success: function (result) {
                            main.page.showDialogCantDrop(result['stateInfo']);
                            if (result['result']) {
                                $("#myModal-1").on('hide.bs.modal', function () {
                                    productEditorFrom[0].reset();
                                    productIdInput.val("");
                                    window.location.reload();
                                })
                            }
                        }
                    });
                }
            });

        },
        showAdmin: function () {


            $("#addProduct").click(function () {
                var editorModal = $("#myModal-4");
                var modalTitle = $("#editorTitle");
                var ProductImgShow = $("#productImgShow");
                var productIdInput = $("#productId");
                ProductImgShow.attr("src", "/images/default.jpg");
                productIdInput.val("");

                modalTitle.html("新增商品");
                editorModal.modal("show");

            });
            $(".teaID").each(function (index) {
                var productEditor = $("#productEditor" + index);
                var productDel = $("#productDel" + index);

                productEditor.click(function () {
                    var productImg = $("#productImg" + index).val();
                    var ProductImgShow = $("#productImgShow");
                    var productName = $("#productName" + index).html();
                    var productNameInput = $("#productName");
                    var productStocksInput = $("#productStocks");
                    var productPrice = $("#productPrice" + index).html();
                    var productPriceInput = $("#productPrice");
                    var productId = $("#productId" + index).val();
                    var productIdInput = $("#productId");
                    var editorModal = $("#myModal-4");
                    var modalTitle = $("#editorTitle");
                    var image = "/images/" + productImg;
                    modalTitle.html("编辑商品");
                    ProductImgShow.attr("src", image);
                    productIdInput.val(productId);
                    productNameInput.val(productName);
                    productPriceInput.val(productPrice);
                    productStocksInput.val(0);
                    editorModal.modal("show");
                });

                productDel.click(function () {
                    var productId = $("#productId" + index).val();
                    productDel.attr("disabled", "disabled");
                    if (confirm("确认删除？")) {
                        $.post(main.URL.productDel(productId), {}, function (result) {
                            main.page.showDialogCantDrop(result['stateInfo']);
                            if (result['result']) {
                                $("#myModal-1").on('hide.bs.modal', function () {
                                    window.location.reload();
                                })
                            }
                        });
                    }
                    productDel.removeAttr("disabled");
                });

            });
        }

    }
};