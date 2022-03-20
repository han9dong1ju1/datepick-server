package app.hdj.datepick.global.util;


import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import org.springframework.stereotype.Component;

import static app.hdj.datepick.domain.place.entity.QPlace.place;
import static com.querydsl.core.types.dsl.MathExpressions.*;

@Component
public class GeoQueryUtil {

    public NumberExpression<Double> getDistanceExpression(Double latitude, Double longitude) {
        return acos(sin(radians(Expressions.constant(latitude)))
                .multiply(sin(radians(place.latitude)))
                .add(cos(radians(Expressions.constant(latitude))).multiply(cos(radians(place.latitude)))
                        .multiply(cos(radians(Expressions.constant(longitude)).subtract(radians(place.longitude)))))).multiply(6371);

    }

}
