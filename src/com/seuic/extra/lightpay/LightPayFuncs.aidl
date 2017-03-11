package com.seuic.extra.lightpay;

import com.seuic.extra.lightpay.LightPayListener;

interface LightPayFuncs {
	int start(LightPayListener listener,int overtime);
	int cancel();
}
