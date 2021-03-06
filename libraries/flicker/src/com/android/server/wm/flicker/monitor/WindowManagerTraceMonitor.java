/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.server.wm.flicker.monitor;

import android.os.RemoteException;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;

import java.nio.file.Path;

/** Captures WindowManager trace from WindowManager. */
public class WindowManagerTraceMonitor extends TraceMonitor {
    private IWindowManager mWm = WindowManagerGlobal.getWindowManagerService();

    public WindowManagerTraceMonitor() {
        this(OUTPUT_DIR);
    }

    public WindowManagerTraceMonitor(Path outputDir) {
        super(outputDir, "wm_trace.pb");
    }

    @Override
    public void start() {
        try {
            mWm.startWindowTrace();
        } catch (RemoteException e) {
            throw new RuntimeException("Could not start trace", e);
        }
    }

    @Override
    public void stop() {
        try {
            mWm.stopWindowTrace();
        } catch (RemoteException e) {
            throw new RuntimeException("Could not stop trace", e);
        }
    }

    @Override
    public boolean isEnabled() throws RemoteException {
        return mWm.isWindowTraceEnabled();
    }
}
