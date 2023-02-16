package com.goofy.goober.shaders

import android.graphics.RuntimeShader
import org.intellij.lang.annotations.Language

// Shadertoy
@Language("AGSL")
val NoiseGrain1 = """
    uniform float2 resolution;
    uniform shader contents; 
    
    vec4 main( vec2 fragCoord )
    {
        vec2 uv = fragCoord/resolution.xy;
        
        // Check if pixel is inside viewport bounds
        if (fragCoord.x < 0.0 || fragCoord.x > resolution.x || fragCoord.y < 0.0 || fragCoord.y > resolution.y) {
            return vec4(contents.eval(fragCoord));
        }

        float mdf = -0.8 * 0.1; // increase for noise amount 
        float noise = (fract(sin(dot(uv, vec2(12.9898,78.233)*2.0)) * 43758.5453));
        vec4 tex = vec4(contents.eval(fragCoord));
        
        mdf *= 1.5;
        
        vec4 col = tex - noise * mdf;

        return col;
    }
""".trimIndent()

// https://simonharris.co/making-a-noise-film-grain-post-processing-effect-from-scratch-in-threejs/
@Language("AGSL")
val NoiseGrain2 = """
    uniform float2 resolution;
    uniform shader contents; 
    
    float random( vec2 p )
    {
        vec2 K1 = vec2(
            23.14069263277926, // e^pi (Gelfond's constant)
            2.665144142690225 // 2^sqrt(2) (Gelfond–Schneider constant)
        );
        return fract( cos( dot(p,K1) ) * 43758.5453 );
    }
    
    vec4 main( vec2 fragCoord )  {
        // Normalized pixel coordinates (from 0 to 1)
        vec2 uv = fragCoord/resolution.xy;
        
        // Check if pixel is inside viewport bounds
        if (fragCoord.x < 0.0 || fragCoord.x > resolution.x || fragCoord.y < 0.0 || fragCoord.y > resolution.y) {
            return vec4(contents.eval(fragCoord));
        }
        
        vec2 uvRandom = uv;
        float amount = 0.2;
        uvRandom.y *= random(vec2(uvRandom.y,amount));
        vec4 tex = vec4(contents.eval(fragCoord));
        tex.rgb += random(uvRandom)*0.15;
    
        return vec4(tex);
    }
""".trimIndent()

// Lighter grain by varying the * 0.15 + 0.16 coefficients
// https://github.com/Robpayot/risograph-grain-shader/blob/master/src/demo1/js/shaders/grain.frag
@Language("AGSL")
val Risograph = """
    uniform float2 resolution;
    uniform shader contents; 
    
    float random( vec2 p )
    {
        vec2 K1 = vec2(
            23.14069263277926, // e^pi (Gelfond's constant)
            2.665144142690225 // 2^sqrt(2) (Gelfond–Schneider constant)
        );
        return fract( cos( dot(p,K1) ) * 43758.5453 );
    }
    
    float noise( vec2 uv )
    {
      vec2 K1 = vec2(12.9898,78.233);
    	return (fract(sin(dot(uv, K1*2.0)) * 43758.5453));
    }
    
    vec4 main( vec2 fragCoord )  {
        // Normalized pixel coordinates (from 0 to 1)
        vec2 uv = fragCoord/resolution.xy;
        
        // Check if pixel is inside viewport bounds
        if (fragCoord.x < 0.0 || fragCoord.x > resolution.x || fragCoord.y < 0.0 || fragCoord.y > resolution.y) {
            return vec4(contents.eval(fragCoord));
        }
        
        vec2 uvRandom = uv;
        float amount = 0.8;
        uvRandom.y *= noise(vec2(uvRandom.y,amount));
        vec4 tex = vec4(contents.eval(fragCoord));
        vec4 originalTex = tex;
        tex.rgb += random(uvRandom) * 0.15 + 0.16;
      
        
        float r = max(tex.r, originalTex.r);
        float g = max(tex.g, originalTex.g);
        float b = max(tex.b, originalTex.b);
        float a = 1.0;
      
        return vec4(r, g, b, a);
    }
""".trimIndent()

@Language("AGSL")
val PaperTexture = RuntimeShader(
    """
    uniform float2 resolution;
    uniform shader contents; 

    vec4 noise2(vec2 uv) {
      vec4 n = vec4(fract(sin(dot(uv.xy, vec2(12.9898,78.233))) * 43758.5453));
      return vec4(n.x, n.y, n.z, n.w);
    }
    
    vec4 main( vec2 fragCoord )  {
      vec2 uv = fragCoord.xy / resolution.xy;
      
      // Check if pixel is inside viewport bounds
      if (fragCoord.x < 0.0 || fragCoord.x > resolution.x || fragCoord.y < 0.0 || fragCoord.y > resolution.y) {
          return vec4(contents.eval(fragCoord));
      }
      
      vec4 grain = vec4(noise2(fragCoord * 10.0).r - 0.5);
      vec4 fiber = vec4(noise2(uv * 20.0).g - 0.5);
      vec4 dots = vec4(noise2(fragCoord * 30.0).b - 0.5);
      
      vec4 randomSpecs = vec4(0.0, 0.0, 0.0, 0.0);
      if (fract(dots.x * 10.0) > 0.8) {
        randomSpecs = vec4(0.1, 0.1, 0.1, 1.0);
      }
      
      vec4 randomFibers = vec4(0.0, 0.0, 0.0, 0.0);
      if (fract(fiber.y * 10.0 + uv.y * 10.0) > 0.95) {
        randomFibers = vec4(0.2, 0.2, 0.2, 1.0);
      }
    
      float fiberThickness = fract(fiber.y * 20.0 + uv.y * 10.0) * 0.05 + 0.01;
      vec4 squigglyFibers = vec4(1.0, 1.0, 1.0, 0.0);
      if (fract(fiber.y * 40.0 + uv.y * 20.0) > 0.95) {
        squigglyFibers = vec4(1.0, 1.0, 1.0, fiberThickness);
      }
      
      return min(vec4(contents.eval(fragCoord)) 
        + grain * 0.05 
        + fiber * 0.5
        + randomSpecs, squigglyFibers);
    }
""".trimIndent()
)


