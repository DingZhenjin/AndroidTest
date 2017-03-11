package com.seuic.extra.lightpay;


interface LightPayListener {
	int OnSuccess(in byte[] lightdata,int lightdatalen,in byte[] hardwareID);
	int OnFail(int returncode);
}
