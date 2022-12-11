/*!
 * Remark (http://getbootstrapadmin.com/remark)
 * Copyright 2017 amazingsurge
 * Licensed under the Themeforest Standard Licenses
 */

!function(global,factory){if("function"==typeof define&&define.amd)define("/pages/faq",["jquery","Site"],factory);else if("undefined"!=typeof exports)factory(require("jquery"),require("Site"));else{var mod={exports:{}};factory(global.jQuery,global.Site),global.pagesFaq=mod.exports}}(this,function(_jquery,_Site){"use strict";var _jquery2=babelHelpers.interopRequireDefault(_jquery),Site=babelHelpers.interopRequireWildcard(_Site);(0,_jquery2.default)(document).ready(function(){Site.run(),(0,_jquery2.default)(".faq-list").length&&(0,_jquery2.default)('a[data-toggle="tab"]').on("shown.bs.tab",function(e){(0,_jquery2.default)(e.target).addClass("active").siblings().removeClass("active")})})});