/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.netease.glsurfaceView.utils;

public class NumberUtils {
  static public final float RADIANS_TO_DEGREES = (float) (180f / Math.PI);

  public static int floatToIntBits(float value) {
    return Float.floatToIntBits(value);
  }

  public static int floatToRawIntBits(float value) {
    return Float.floatToRawIntBits(value);
  }

  public static int floatToIntColor(float value) {
    return Float.floatToRawIntBits(value);
  }

  /**
   * Encodes the ABGR int color as a float. The high bits are masked to avoid
   * using floats in the NaN range, which unfortunately means the full range of
   * alpha cannot be used. See {@link Float#intBitsToFloat(int)} javadocs.
   */
  public static float intToFloatColor(int value) {
    return Float.intBitsToFloat(value & 0xfeffffff);
  }

  public static float intBitsToFloat(int value) {
    return Float.intBitsToFloat(value);
  }

  public static long doubleToLongBits(double value) {
    return Double.doubleToLongBits(value);
  }

  public static double longBitsToDouble(long value) {
    return Double.longBitsToDouble(value);
  }

  public static float cosDeg(float deg) {
    return (float) Math.cos(deg * Math.PI / 180);
  }

  public static float sinDeg(float deg) {
    return (float) Math.sin(deg * Math.PI / 180);
  }

  // ---

  static public int clamp(int value, int min, int max) {
    if (value < min) {
      return min;
    }
    if (value > max) {
      return max;
    }
    return value;
  }

  static public short clamp(short value, short min, short max) {
    if (value < min) {
      return min;
    }
    if (value > max) {
      return max;
    }
    return value;
  }

  static public float clamp(float value, float min, float max) {
    if (value < min) {
      return min;
    }
    if (value > max) {
      return max;
    }
    return value;
  }

  static public int nextPowerOfTwo(int value) {
    if (value == 0) {
      return 1;
    }
    value--;
    value |= value >> 1;
    value |= value >> 2;
    value |= value >> 4;
    value |= value >> 8;
    value |= value >> 16;
    return value + 1;
  }

  static public boolean isPowerOfTwo(int value) {
    return value != 0 && (value & value - 1) == 0;
  }
}
