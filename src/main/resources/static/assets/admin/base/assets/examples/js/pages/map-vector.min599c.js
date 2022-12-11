/*!
 * Remark (http://getbootstrapadmin.com/remark)
 * Copyright 2017 amazingsurge
 * Licensed under the Themeforest Standard Licenses
 */

!function(global,factory){if("function"==typeof define&&define.amd)define("/pages/map-vector",["jquery","Site"],factory);else if("undefined"!=typeof exports)factory(require("jquery"),require("Site"));else{var mod={exports:{}};factory(global.jQuery,global.Site),global.pagesMapVector=mod.exports}}(this,function(_jquery,_Site){"use strict";var _jquery2=babelHelpers.interopRequireDefault(_jquery),Site=babelHelpers.interopRequireWildcard(_Site);(0,_jquery2.default)(document).ready(function(){Site.run();var defaults=Plugin.getDefaults("vectorMap"),options=_jquery2.default.extend({},defaults,{markers:[{latLng:[1.3,103.8],name:"940 Visits"},{latLng:[51.511214,-.119824],name:"530 Visits"},{latLng:[40.714353,-74.005973],name:"340 Visits"},{latLng:[-22.913395,-43.20071],name:"1,800 Visits"}]},!0);(0,_jquery2.default)("#world-map").vectorMap(options)})});