package pl.training.payments.domain;

import lombok.Value;

@Value
public class ConstantDiscountCalculator implements DiscountCalculator {

    long value;

}
