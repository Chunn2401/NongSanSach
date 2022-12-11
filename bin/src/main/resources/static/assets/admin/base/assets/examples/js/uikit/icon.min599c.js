/*!
 * Remark (http://getbootstrapadmin.com/remark)
 * Copyright 2017 amazingsurge
 * Licensed under the Themeforest Standard Licenses
 */

!function(global,factory){if("function"==typeof define&&define.amd)define("/uikit/icon",["jquery","Site"],factory);else if("undefined"!=typeof exports)factory(require("jquery"),require("Site"));else{var mod={exports:{}};factory(global.jQuery,global.Site),global.uikitIcon=mod.exports}}(this,function(_jquery,_Site){"use strict";var _jquery2=babelHelpers.interopRequireDefault(_jquery),Site=babelHelpers.interopRequireWildcard(_Site);(0,_jquery2.default)(document).ready(function($){Site.run(),$("#icon_change").asRange({tip:!1,scale:!1,onChange:function(value){$("#icon_size").text(value+"px"),$(".panel .icon").css("font-size",value)}}),$(".input-search input[type=text]").on("keyup",function(){var val=$(this).val();""!==val?($("[data-name]").addClass("is-hide"),$("[data-name*="+val+"]").removeClass("is-hide")):$("[data-name]").removeClass("is-hide"),$(".icon-group").each(function(){var $group=$(this);0===$group.find("[data-name]:not(.is-hide)").length?$group.hide():$group.show()})})})});