function checkType(value){

    /**
    1. 반차인 경우
        - 종료일 lock 및 value 초기화
        - ampm : 오전으로 초기화 및 종일 option hide

    2. 연차인 경우
        - 종료일 lock 해제
        - ampm : 종일로 초기화 후 lock
    */
    
    if(value === "반차"){

        $("input[name=endDate]").attr("disabled",true); 
        $("input[name=endDate]").val('');
        $('select[name=ampm]').val('오전').attr("selected", "selected");
        $('select[name=ampm]').attr("disabled",false);
        $(".allDay").hide();

    }else if(value === "연차"){

            $("input[name=endDate]").attr("disabled",false); 
            $('select[name=ampm]').val('종일').attr("selected", "selected");
            $('select[name=ampm').attr("disabled",true);

    }

}
