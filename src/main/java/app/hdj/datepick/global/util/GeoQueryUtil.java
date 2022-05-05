package app.hdj.datepick.global.util;


import static com.querydsl.core.types.dsl.MathExpressions.acos;
import static com.querydsl.core.types.dsl.MathExpressions.cos;
import static com.querydsl.core.types.dsl.MathExpressions.radians;
import static com.querydsl.core.types.dsl.MathExpressions.sin;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;

public class GeoQueryUtil {

    public static NumberExpression<Double> getDistanceExpression(
        Double latitude,
        Double longitude,
        NumberPath<Double> pathLatitude,
        NumberPath<Double> pathLongitude
    ) {
        return acos(
            sin(radians(Expressions.constant(latitude))).multiply(sin(radians(pathLatitude))).add(
                cos(radians(Expressions.constant(latitude))).multiply(cos(radians(pathLatitude)))
                    .multiply(cos(radians(Expressions.constant(longitude)).subtract(
                        radians(pathLongitude)))))).multiply(6371);

    }
}
