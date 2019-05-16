package com.service.consumer;

import org.springframework.context.annotation.Bean;

import feign.Contract;

public class FeignConfiguration {
	/**
	 * 将契约改成Feign原生的默认契约，从而使用Feign自带的注解
	 */
	@Bean
	public Contract feignContract() {
		return new feign.Contract.Default();
	}
}
