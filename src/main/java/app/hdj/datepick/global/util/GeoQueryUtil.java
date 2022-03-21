package app.hdj.datepick.global.util;


import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import org.springframework.stereotype.Component;

import static com.querydsl.core.types.dsl.MathExpressions.*;

@Component
public class GeoQueryUtil {

    public NumberExpression<Double> getDistanceExpression(Double latitude, Double longitude, NumberPath<Double> pathLatitude, NumberPath<Double> pathLongitude) {
        return acos(sin(radians(Expressions.constant(latitude)))
                .multiply(sin(radians(pathLatitude)))
                .add(cos(radians(Expressions.constant(latitude))).multiply(cos(radians(pathLatitude)))
                        .multiply(cos(radians(Expressions.constant(longitude)).subtract(radians(pathLongitude)))))).multiply(6371);

    }

}
