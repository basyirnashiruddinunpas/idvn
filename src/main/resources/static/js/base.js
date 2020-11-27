function urlify(text) {
  var urlRegex = /(https?:\/\/[^\s]+)/g;
  return text.replace(urlRegex, function(url) {
    return '<a href="' + url + '">' + url + '</a>';
  })
  // or alternatively
  // return text.replace(urlRegex, '<a href="$1">$1</a>')
}
$("p").each(function(){
    $(this).html(urlify($(this).html()));
});
$(".urlable").each(function(){
    $(this).html(urlify($(this).html()));
});