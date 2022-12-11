/*!
 * Remark (http://getbootstrapadmin.com/remark)
 * Copyright 2017 amazingsurge
 * Licensed under the Themeforest Standard Licenses
 */

!function(global,factory){if("function"==typeof define&&define.amd)define("/layouts/panel-transition",["jquery","Site"],factory);else if("undefined"!=typeof exports)factory(require("jquery"),require("Site"));else{var mod={exports:{}};factory(global.jQuery,global.Site),global.layoutsPanelTransition=mod.exports}}(this,function(_jquery,_Site){"use strict";var _jquery2=babelHelpers.interopRequireDefault(_jquery),Site=babelHelpers.interopRequireWildcard(_Site);(0,_jquery2.default)(document).ready(function($){Site.run();var $example=$("#exampleTransition");$(document).on("click.panel.transition","[data-type]",function(){var type=$(this).data("type");$example.data("animateList").run(type)}),$(document).on("close.uikit.panel","[class*=blocks-] > li > .panel",function(){$(this).parent().hide()})})});