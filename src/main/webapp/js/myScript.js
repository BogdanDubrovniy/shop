function deleteGoodByID(goodID) {
    var goodNumber = +goodID;
    var xmlhttp = getXmlHttp();
    xmlhttp.open('POST', 'deleteById?id=' + goodNumber, true);
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {
            if (xmlhttp.status == 200) {
                var elementId = "item" + goodNumber;
                document.getElementById(elementId).innerHTML = "deleted";
                document.getElementById(elementId).setAttribute("disabled", "disabled");
                xmlhttp.abort();
            }
        }
    };
    xmlhttp.send(null);
}

function getXmlHttp() {
    var xmlhttp;
    try {
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
        try {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (E) {
            xmlhttp = false;
        }
    }
    if (!xmlhttp && typeof XMLHttpRequest != 'undefined') {
        xmlhttp = new XMLHttpRequest();
    }
    return xmlhttp;
}