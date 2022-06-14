#include "hal.h"

const int DELAY_IN_MILLISECONDS = 500;
const int N = 0x8;
const int NUMBER_OF_FRAMES = 8;
const int NUMBER_OF_LEDS = 8;
static bool FRAMES[NUMBER_OF_FRAMES][NUMBER_OF_LEDS] = {
        {false, false, false, false, false, false, true,  true},
        {false, false, false, false, false, true,  true,  false},
        {false, false, false, false, true,  true,  false, false},
        {false, false, false, true,  true,  false, false, false},
        {false, false, true,  true,  false, false, false, false},
        {false, true,  true,  false, false, false, false, false},
        {true,  true,  false, false, false, false, false, false},
        {true,  false, false, false, false, false, false, true}
};

static unsigned int leds_num[NUMBER_OF_LEDS] = {GPIO_PIN_3, GPIO_PIN_4, GPIO_PIN_5,
                                                GPIO_PIN_6, GPIO_PIN_8, GPIO_PIN_9,
                                                GPIO_PIN_11, GPIO_PIN_12};

static unsigned int sw_num[4] = {GPIO_PIN_4, GPIO_PIN_8, GPIO_PIN_10, GPIO_PIN_12};

int get_sw_value() {
    int num = 0, pow = 1;
    for (int i = 0; i < 4; i++) {
        num += pow * HAL_GPIO_ReadPin(GPIOE, sw_num[i]);
        pow <<= 1;
    }
    return num;
}

void set_VD6_to_green() {
    HAL_GPIO_WritePin(GPIOD, GPIO_PIN_13, GPIO_PIN_SET);
    HAL_GPIO_WritePin(GPIOD, GPIO_PIN_14, GPIO_PIN_RESET);
    HAL_GPIO_WritePin(GPIOD, GPIO_PIN_15, GPIO_PIN_RESET);
}

void set_VD7_to_yellow() {
    HAL_GPIO_WritePin(GPIOD, GPIO_PIN_13, GPIO_PIN_RESET);
    HAL_GPIO_WritePin(GPIOD, GPIO_PIN_14, GPIO_PIN_SET);
    HAL_GPIO_WritePin(GPIOD, GPIO_PIN_15, GPIO_PIN_RESET);
}

void set_VD7_to_red() {
    HAL_GPIO_WritePin(GPIOD, GPIO_PIN_13, GPIO_PIN_RESET);
    HAL_GPIO_WritePin(GPIOD, GPIO_PIN_14, GPIO_PIN_RESET);
    HAL_GPIO_WritePin(GPIOD, GPIO_PIN_15, GPIO_PIN_SET);
}

void set_by_frame(int frame) {
    for (int j = 0; j < NUMBER_OF_LEDS; ++j) {
        if (FRAMES[frame][j]) HAL_GPIO_WritePin(GPIOD, leds_num[j], GPIO_PIN_SET);
        else HAL_GPIO_WritePin(GPIOD, leds_num[j], GPIO_PIN_RESET);
    }
}

void reset_frame() {
    for (int i = 0; i < NUMBER_OF_LEDS; ++i) {
        HAL_GPIO_WritePin(GPIOD, leds_num[i], GPIO_PIN_RESET);
    }
}

void show_animation() {
    set_VD6_to_green();
    reset_frame();
    for (int i = 0; i < NUMBER_OF_FRAMES; ++i) {
        set_by_frame(i);
        HAL_Delay(DELAY_IN_MILLISECONDS);
        if (HAL_GPIO_ReadPin(GPIOC, GPIO_PIN_15) == GPIO_PIN_RESET) {
            set_VD7_to_red();
            HAL_Delay(DELAY_IN_MILLISECONDS);
            GPIO_PinState state = GPIO_PIN_SET;
            while (state == GPIO_PIN_SET) {
                state = HAL_GPIO_ReadPin(GPIOC, GPIO_PIN_15);
                HAL_Delay(DELAY_IN_MILLISECONDS); // fixes bug when 2 times blocked but 1 requested
            }
            set_VD6_to_green();
        }
    }
    reset_frame();
}


void show_by_sw() {
    set_VD7_to_yellow();
    for (int i = 0; i < 4; i++) {
        GPIO_PinState state = HAL_GPIO_ReadPin(GPIOE, sw_num[i]);
        HAL_GPIO_WritePin(GPIOD, leds_num[i], state);
    }
}

int umain() {
    while (true) {
        if (get_sw_value() == N)
            show_animation();
        else
            show_by_sw();
        HAL_Delay(DELAY_IN_MILLISECONDS);
    }
    return 0;
}