var portfolio = null;

var Portfolio = function (name) {

};

Portfolio.prototype.doSomething = function() {
    return null;
};

Portfolio.prototype.loading = function () {
    $.ajax({
        type: "GET",
        url: "/portfolio/loading",
        success: function (ret) {
            portfolio.draw(ret);
        },
        error: function (error) {
            console.log("failed");
        },
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        timeout: 1000
    });
};

Portfolio.prototype.clearRecords = function () {
    $("#portfolio tbody").empty();
}

Portfolio.prototype.paintRecord = function (rec) {
    let newRowContent = "<tr>" +
                            "<td>" +
                                rec.pair +
                            "</td>" +
                            "<td>" +
                                rec.amount_of_coin +
                            "</td>" +
                            "<td>" +
                                rec.date +
                            "</td>" +
                        "</tr>";
    $("#portfolio tbody").append(newRowContent);
};

Portfolio.prototype.draw = function (ret) {
    if (ret && ret.data && ret.data.length > 0) {
        portfolio.clearRecords();
        for (let i=0;i<15;i++) {
            portfolio.paintRecord(ret.data[i]);
        }
    } else {
        portfolio.clearRecords();
    }
};

$( document ).ready(function() {

    $.ajaxSetup({
        headers:
            { 'X-CSRF-TOKEN': $('meta[name="meta_csrf"]').attr('content') }
    });

    portfolio = new Portfolio($("#userName").text());
    portfolio.loading();
    setInterval(portfolio.loading, 5000);
});