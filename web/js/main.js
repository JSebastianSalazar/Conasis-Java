
$(document).ready(function () {
    $(".dropdown").hide();
    $("#imglol").click(function () {
        $(".dropdown").toggle();

   
            var X = $(this).attr('id');
            if (X === 1) {
                $(".submenu").hide();
                $(this).attr('id', '0');
            }
            else {
                $(".submenu").show();
                $(this).attr('id', '1');
            }
    

        //Mouse click on sub menus  
        $(".submenu").mouseup(function () {
            return false;
        });

        //Mouse click on my account link  
        $(".mainmenu").mouseup(function () {
            return false;
        });


        //On Document Click  
        $(document).mouseup(function () {
            $(".submenu").hide();
            $(".mainmenu").attr('id', '');
        });
    });
});