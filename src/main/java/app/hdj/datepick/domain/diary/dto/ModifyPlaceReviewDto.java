package app.hdj.datepick.domain.diary.dto;


import lombok.Getter;

@Getter
public class ModifyPlaceReviewDto {
    //place Order 만 지정하면 place Id 는 알아서 지정해주도록.
    private Byte placeOrder;
    private Float rating;
    private String content;
}

