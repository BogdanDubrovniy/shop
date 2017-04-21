<div class="h3">
    Sort the goods:
</div>
<form action="<c:url value="/sortGoods"/>" method="post">
    Have your choice:
    <label>
        <select name="selectField">
            <option value="good_category"> - sort by category</option>
            <option value="good_name"> - sort by name</option>
            <option value="good_price"> - sort by price</option>
        </select>
        <label>
            <select name="selectWay">
                <option value="">forward direction</option>
                <option value=" desc">reverse direction</option>
            </select>
        </label>
    </label>

    <button type="submit" class="btn-success">
        sort goods
    </button>
</form>