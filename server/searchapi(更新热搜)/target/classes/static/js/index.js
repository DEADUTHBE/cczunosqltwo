function showHidden() {
    var dis = document.getElementById('hidden');
    if (dis.style.display == 'none') {
    document.getElementById('show').innerHTML = "关闭 ";
    dis.style.display = "";
} else {
    document.getElementById('show').innerHTML = "展开 ";
    dis.style.display = "none";
    }
}