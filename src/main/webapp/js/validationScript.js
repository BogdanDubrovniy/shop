$(document).ready(function () {
    var jVal = {
        'userName': function () {
            $('body').append('<div id="nameInfo" class="info"></div>');
            var nameInfo = $('#nameInfo');
            var element = $('#userName');

            var patt = /^[а-яА-Яa-zA-Z]{5,19}$/i;
            if (!patt.test(element.val())) {
                jVal.errors = true;
                alert('Wrong name format');
                element.removeClass('normal').addClass('wrong');
            } else {
                element.removeClass('wrong').addClass('normal');
            }
        },

        'userSurname': function () {
            $('body').append('<div id="surnameInfo" class="info"></div>');
            var element = $('#userSurname');
            var patt = /^[а-яА-Яa-zA-Z]{5,19}$/i;
            if (!patt.test(element.val())) {
                jVal.errors = true;
                alert('Wrong surname format');
                element.removeClass('normal').addClass('wrong');
            } else {
                element.removeClass('wrong').addClass('normal');
            }
        },

        'userLogin': function () {
            $('body').append('<div id="userLoginInfo" class="info"></div>');
            var element = $('#userLogin');
            var patt = /^[а-яА-Яa-zA-Z0-9]{4,16}$/i;
            if (!patt.test(element.val())) {
                jVal.errors = true;
                alert('Wrong Login Format!');
                element.removeClass('normal').addClass('wrong');
            } else {
                element.removeClass('wrong').addClass('normal');
            }
        },

        'email': function () {
            $('body').append('<div id="emailInfo" class="info"></div>');
            var emailInfo = $('#emailInfo');
            var element = $('#email');
            var patt = /^.+@.+[.].{2,}$/i;
            if (!patt.test(element.val())) {
                jVal.errors = true;
                alert('Please, enter valid email');
                element.removeClass('normal').addClass('wrong');
            } else {
                element.removeClass('wrong').addClass('normal');
            }
        },

        'userPassword': function () {
            $('body').append('<div id="userPasswordInfo" class="info"></div>');
            var element = $('#userPassword');
            var patt = /^[а-яА-Яa-zA-Z0-9]{4,16}$/i;
            if (!patt.test(element.val())) {
                jVal.errors = true;
                alert('Please, enter valid password');
                element.removeClass('normal').addClass('wrong');
            } else {
                element.removeClass('wrong').addClass('normal');
            }
        },

        'userPasswordConfirmed': function () {
            $('body').append('<div id="userPasswordConfirmedInfo" class="info"></div>');
            var element = $('#userPasswordConfirmed');
            var elementPrevious = $('#userPassword');
            if (elementPrevious.val() == element.val()) {
                element.removeClass('wrong').addClass('normal');
            } else {
                jVal.errors = true;
                alert('Please, confirm valid password');
                element.removeClass('normal').addClass('wrong');
            }
        },

        'sendIt': function () {
            if (!jVal.errors) {
                $('#jform').submit();
            }
        }
    };

    $('#send').click(function () {
        var obj = $.browser.webkit ? $('body') : $('html');
        obj.animate({scrollTop: $('#jform').offset().top}, 750, function () {
            jVal.errors = false;
            jVal.userName();
            jVal.userSurname();
            jVal.userLogin();
            jVal.email();
            jVal.userPassword();
            jVal.userPasswordConfirmed();
            jVal.sendIt();
        });
        return false;
    });
    $('#userName').change(jVal.userName);
    $('#userSurname').change(jVal.userSurname);
    $('#userLogin').change(jVal.userLogin);
    $('#email').change(jVal.email);
    $('#userPassword').change(jVal.userPassword);
    $('#userPasswordConfirmed').change(jVal.userPasswordConfirmed);
});