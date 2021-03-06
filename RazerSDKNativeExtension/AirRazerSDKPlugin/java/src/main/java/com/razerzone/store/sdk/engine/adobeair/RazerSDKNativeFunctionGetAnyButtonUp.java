/*
 * Copyright (C) 2012-2016 Razer, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.razerzone.store.sdk.engine.adobeair;

import android.util.Log;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.razerzone.store.sdk.Controller;

public class RazerSDKNativeFunctionGetAnyButtonUp implements FREFunction {
	
	private static final String TAG = RazerSDKNativeFunctionGetAnyButtonUp.class.getSimpleName();
	
	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		
		try {
			int button;
			if (args.length > 0) {
				button = args[0].getAsInt();
			} else {
				Log.e(TAG, "Missing button:int argument");
				return FREObject.newObject(false);
			}
			
			for (int playerNum = 0; playerNum < Controller.MAX_CONTROLLERS; ++playerNum) {
				boolean result = InputView.getButtonUp(playerNum, button);
				if (result) {
					return FREObject.newObject(true);
				}
			}			

			return FREObject.newObject(false);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "Unexpected exception");
		}
		
		return null;
	}
}
