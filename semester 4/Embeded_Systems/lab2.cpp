#include <stdlib.h>
#include "hal.h"

int current_index_of_left_light = 0;
bool is_edge_reached = false;
const int initial_delay = 500;

int leds_num[] = {GPIO_PIN_3, GPIO_PIN_4, GPIO_PIN_5,
                  GPIO_PIN_6, GPIO_PIN_8, GPIO_PIN_9,
                  GPIO_PIN_11, GPIO_PIN_12};


void clear_all_lights() {
    for (int k = 0; k < 8; k++)
        HAL_GPIO_WritePin(GPIOD, leds_num[k], GPIO_PIN_RESET);
}


void TIM7_IRQ_HANDLER() {
    clear_all_lights();
    HAL_GPIO_WritePin(GPIOD, leds_num[current_index_of_left_light], GPIO_PIN_SET);
    HAL_GPIO_WritePin(GPIOD, leds_num[current_index_of_left_light + 4], GPIO_PIN_SET);

    if (current_index_of_left_light == 0)
        is_edge_reached = false;
    else if (current_index_of_left_light == 3)
        is_edge_reached = true;

    if (is_edge_reached) current_index_of_left_light--;
    else current_index_of_left_light++;
}


int umain() {
    char switches_number[4] = {'0', '0', '0', '0',};
    int gpio_pins[] = {GPIO_PIN_4, GPIO_PIN_8, GPIO_PIN_10, GPIO_PIN_12};
    int delay = initial_delay;
    int prev_delay = delay;
    registerTIM7_IRQHandler(TIM7_IRQ_HANDLER);
    __enable_irq();

    WRITE_REG(TIM7_ARR, initial_delay);
    WRITE_REG(TIM7_DIER, TIM_DIER_UIE);
    WRITE_REG(TIM7_PSC, 0);
    WRITE_REG(TIM7_CR1, 1);


    while (true) {
        for (int i = 0; i < 4; ++i) {
            switches_number[i] = HAL_GPIO_ReadPin(GPIOE, gpio_pins[i]) == GPIO_PIN_RESET ? '0' : '1';
        }

        delay = 500 + 100 * strtol(switches_number, NULL, 2);

        if (delay != prev_delay) {
            WRITE_REG(TIM7_CR1, 0);
            __disable_irq();//фиксит баг, когда при тыкание свичей программа умирает
            WRITE_REG(TIM7_ARR, delay);
            WRITE_REG(TIM7_DIER, TIM_DIER_UIE);
            WRITE_REG(TIM7_PSC, 0);
            __enable_irq();
            WRITE_REG(TIM7_CR1, 1);
        }
        prev_delay = delay;
    }
    return 0;
}