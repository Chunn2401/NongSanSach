WebFontConfig = {
    google: { families: ["Muli:regular,700", "Muli:regular,regular", "Muli:regular,700", "Prompt:regular,800",] }
};
(function () {
    var wf = document.createElement('script');
    wf.src = 'https://ajax.googleapis.com/ajax/libs/webfont/1/webfont.js';
    wf.type = 'text/javascript';
    wf.async = 'true';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(wf, s);
})();