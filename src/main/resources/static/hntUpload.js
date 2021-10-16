var Upload = function (file) {
    this.file = file;
};

var upload = null;

Upload.prototype.getType = function() {
    return this.file.type;
};
Upload.prototype.getSize = function() {
    return this.file.size;
};
Upload.prototype.getName = function() {
    return this.file.name;
};
Upload.prototype.doUpload = function () {
    var that = this;
    var formData = new FormData();

    // add assoc key values, this will be posts values
    formData.append("file", this.file, this.getName());
    formData.append("upload_file", true);

    $.ajax({
        type: "POST",
        url: "/portfolio/update",
        xhr: function () {
            var myXhr = $.ajaxSettings.xhr();
            if (myXhr.upload) {
                myXhr.upload.addEventListener('progress', that.progressHandling, false);
            }
            return myXhr;
        },
        success: function (data) {
            console.log("OK");
        },
        error: function (error) {
            console.log("failed");
        },
        async: true,
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        timeout: 60000
    });
};

Upload.prototype.progressHandling = function (event) {
    var percent = 0;
    var position = event.loaded || event.position;
    var total = event.total;
    var progress_bar_id = "#progress-wrp";
    if (event.lengthComputable) {
        percent = Math.ceil(position / total * 100);
    }
    // update progressbars classes so it fits your code
    $(progress_bar_id + " .progress-bar").css("width", +percent + "%");
    $(progress_bar_id + " .status").text(percent + "%");
};

Upload.prototype.clearPortfolio = function () {
    $.ajax({
        type: "POST",
        url: "/portfolio/clear",
        success: function (data) {
            console.log("OK");
        },
        error: function (error) {
            console.log("failed");
        },
        async: true,
        cache: false,
        contentType: false,
        processData: false,
        timeout: 10000
    });
};

//Change id to your id
$( document ).ready(function() {
    console.log( "ready!" );

    $.ajaxSetup({
        headers:
            { 'X-CSRF-TOKEN': $('meta[name="meta_csrf"]').attr('content') }
    });

    $("#clearPortfolio").on("click", function (e) {
        if (upload==null) {
            var file = $("#csv")[0].files[0];
            upload = new Upload(file);
        }
        upload.clearPortfolio();
    });

    $("#updatePortfolio").on("click", function (e) {
        var file = $("#csv")[0].files[0];
        upload = new Upload(file);

        // maby check size or type here with upload.getSize() and upload.getType()
        if (file != null && file.size > 0) {
            // execute upload
            upload.doUpload();
        }
    });
});