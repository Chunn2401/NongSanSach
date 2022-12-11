/*!
* LABELAUTY jQuery Plugin
*
* @file: jquery-labelauty.js
* @author: Francisco Neves (@fntneves)
* @site: www.francisconeves.com
* @license: MIT License
*/(function($){$.fn.labelauty=function(options)
{var settings=$.extend({"development":false,"class":"labelauty","icon":true,"label":true,"separator":"|","checked_label":"Checked","unchecked_label":"Unchecked","force_random_id":false,"minimum_width":false,"same_width":true},options);return this.each(function()
{var $object=$(this);var selected=$object.is(':checked');var type=$object.attr('type');var use_icons=true;var use_labels=true;var labels;var labels_object;var input_id;var aria_label=$object.attr("aria-label");$object.attr("aria-hidden",true);if($object.is(":checkbox")===false&&$object.is(":radio")===false)
return this;$object.addClass(settings["class"]);labels=$object.attr("data-labelauty");use_labels=settings.label;use_icons=settings.icon;if(use_labels===true)
{if(labels==null||labels.length===0)
{labels_object=[settings.unchecked_label,settings.checked_label]}
else
{labels_object=labels.split(settings.separator);if(labels_object.length>2)
{use_labels=false;debug(settings.development,"There's more than two labels. LABELAUTY will not use labels.");}
else
{if(labels_object.length===1)
debug(settings.development,"There's just one label. LABELAUTY will use this one for both cases.");}}}
$object.css({display:"none"});$object.removeAttr("data-labelauty");input_id=$object.attr("id");if(settings.force_random_id||input_id==null||input_id.trim()==="")
{var input_id_number=1+Math.floor(Math.random()*1024000);input_id="labelauty-"+input_id_number;while($(input_id).length!==0)
{input_id_number++;input_id="labelauty-"+input_id_number;debug(settings.development,"Holy crap, between 1024 thousand numbers, one raised a conflict. Trying again.");}
$object.attr("id",input_id);}
var element=jQuery(create(input_id,aria_label,selected,type,labels_object,use_labels,use_icons));element.click(function(){if($object.is(':checked')){$(element).attr('aria-checked',false);}else{$(element).attr('aria-checked',true);}});element.keypress(function(event){event.preventDefault();if(event.keyCode===32||event.keyCode===13){if($object.is(':checked')){$object.prop('checked',false);$(element).attr('aria-checked',false);}else{$object.prop('checked',true);$(element).attr('aria-checked',true);}}});$object.after(element);if(settings.minimum_width!==false)
$object.next("label[for="+input_id+"]").css({"min-width":settings.minimum_width});if(settings.same_width!=false&&settings.label==true)
{var label_object=$object.next("label[for="+input_id+"]");var unchecked_width=getRealWidth(label_object.find("span.labelauty-unchecked"));var checked_width=getRealWidth(label_object.find("span.labelauty-checked"));if(unchecked_width>checked_width)
label_object.find("span.labelauty-checked").width(unchecked_width);else
label_object.find("span.labelauty-unchecked").width(checked_width);}});};function getRealWidth(element)
{var width=0;var $target=element;var css_class='hidden_element';$target=$target.clone().attr('class',css_class).appendTo('body');width=$target.width(true);$target.remove();return width;}
function debug(debug,message)
{if(debug&&window.console&&window.console.log)
window.console.log("jQuery-LABELAUTY: "+message);}
function create(input_id,aria_label,selected,type,messages_object,label,icon)
{var block;var unchecked_message;var checked_message;var aria="";if(messages_object==null)
unchecked_message=checked_message="";else
{unchecked_message=messages_object[0];if(messages_object[1]==null)
checked_message=unchecked_message;else
checked_message=messages_object[1];}
if(aria_label==null)
aria="";else
aria='tabindex="0" role="'+type+'" aria-checked="'+selected+'" aria-label="'+aria_label+'"';if(label==true&&icon==true)
{block='<label for="'+input_id+'" '+aria+'>'+
'<span class="labelauty-unchecked-image"></span>'+
'<span class="labelauty-unchecked">'+unchecked_message+'</span>'+
'<span class="labelauty-checked-image"></span>'+
'<span class="labelauty-checked">'+checked_message+'</span>'+
'</label>';}
else if(label==true)
{block='<label for="'+input_id+'" '+aria+'>'+
'<span class="labelauty-unchecked">'+unchecked_message+'</span>'+
'<span class="labelauty-checked">'+checked_message+'</span>'+
'</label>';}
else
{block='<label for="'+input_id+'" '+aria+'>'+
'<span class="labelauty-unchecked-image"></span>'+
'<span class="labelauty-checked-image"></span>'+
'</label>';}
return block;}}(jQuery));