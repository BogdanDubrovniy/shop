<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="h3">
    Filter the goods:
</div>
<form action="<c:url value="/filterGoods"/>" method="post">
    <legend>Enter information for filter:</legend>
    <p>
        <label>Enter name:</label>
        <input type="text" name="filterName" title="filterName">
    </p>
    <p>
        <label>Enter category:</label>
        <input type="text" name="filterCategory" title="filterCategory">
    </p>
    <p>
        <label>Enter firm:</label>
        <input type="text" name="filterFirm" title="filterFirm">
    </p>
    <p>
        <label>Enter below price:</label>
        <input type="number" name="filterBelowRange" title="filterBelowRange">
    </p>
    <p>
        <label>Enter above price:</label>
        <input type="number" name="filterAboveRange" title="filterAboveRange">
    </p>

    <button type="submit" class="btn-success"> filter goods
    </button>
</form>