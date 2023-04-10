package com.goofy.goober.shaders

import android.graphics.RuntimeShader

/**
 * Shadertoy's default shader
 */
val GradientShader = RuntimeShader(
    """
        uniform float2 resolution;
        uniform float time;
        
        vec4 main(vec2 fragCoord) {
            // Normalized pixel coordinates (from 0 to 1)
            vec2 uv = fragCoord/resolution.xy;
    
            // Time varying pixel color
            vec3 col = 0.8 + 0.2 * cos(time*2.0+uv.xxx*2.0+vec3(1,2,4));
    
            // Output to screen
            return vec4(col,1.0);
        }
    """
)

/**
 * From: https://shaders.skia.org/?id=de2a4d7d893a7251eb33129ddf9d76ea517901cec960db116a1bbd7832757c1f
 */
val NoodleZoomShader = RuntimeShader(
    """
        uniform float2 resolution;
        uniform float time;

        // Source: @notargs https://twitter.com/notargs/status/1250468645030858753
        float f(vec3 p) {
            p.z -= time * 10.;
            float a = p.z * .1;
            p.xy *= mat2(cos(a), sin(a), -sin(a), cos(a));
            return .1 - length(cos(p.xy) + sin(p.yz));
        }
        
        half4 main(vec2 fragcoord) { 
            vec3 d = .5 - fragcoord.xy1 / resolution.y;
            vec3 p=vec3(0);
            for (int i = 0; i < 32; i++) {
              p += f(p) * d;
            }
            return ((sin(p) + vec3(2, 5, 12)) / length(p)).xyz1;
        }
    """
)

/**
 * From: https://www.shadertoy.com/view/4tjSDt
 */
val WarpSpeedShader = RuntimeShader(
    """
        // 'Warp Speed 2'
        // David Hoskins 2015.
        // License Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.

        // Fork of:-   https://www.shadertoy.com/view/Msl3WH
        //----------------------------------------------------------------------------------------
        uniform float2 resolution;      // Viewport resolution (pixels)
        uniform float  time;            // Shader playback time (s)

        vec4 main( in float2 fragCoord )
        {
            float s = 0.0, v = 0.0;
            vec2 uv = (fragCoord / resolution.xy) * 2.0 - 1.;
            float time = (time-2.0)*58.0;
            vec3 col = vec3(0);
            vec3 init = vec3(sin(time * .0032)*.3, .35 - cos(time * .005)*.3, time * 0.002);
            for (int r = 0; r < 100; r++) 
            {
                vec3 p = init + s * vec3(uv, 0.05);
                p.z = fract(p.z);
                // Thanks to Kali's little chaotic loop...
                for (int i=0; i < 10; i++)	p = abs(p * 2.04) / dot(p, p) - .9;
                v += pow(dot(p, p), .7) * .06;
                col +=  vec3(v * 0.2+.4, 12.-s*2., .1 + v * 1.) * v * 0.00003;
                s += .025;
            }
            return vec4(clamp(col, 0.0, 1.0), 1.0);
        }
    """.trimIndent()
)


/**
 * From https://www.shadertoy.com/view/WtBXWw
 */
val LightScatteringShader = RuntimeShader(
    """
        uniform float2 resolution;      // Viewport resolution (pixels)
        uniform float  time;            // Shader playback time (s)
        uniform float2 iMouse;           // Mouse drag pos=.xy Click pos=.zw (pixels)
        
        //Based on Naty Hoffmann and Arcot J. Preetham. Rendering out-door light scattering in real time.
        //http://renderwonk.com/publications/gdm-2002/GDM_August_2002.pdf
        
        const float fov = tan(radians(60.0));
        const float cameraheight = 5e1; //50.
        const float Gamma = 2.2;
        const float Rayleigh = 1.;
        const float Mie = 1.;
        const float RayleighAtt = 1.;
        const float MieAtt = 1.2;

        float g = -0.9;
        
        vec3 _betaR = vec3(1.95e-2, 1.1e-1, 2.94e-1); 
        vec3 _betaM = vec3(4e-2, 4e-2, 4e-2);
        
        const float ts= (cameraheight / 2.5e5);
        
        vec3 Ds = normalize(vec3(0., 0., -1.)); //sun 
        
        vec3 ACESFilm( vec3 x )
        {
            float tA = 2.51;
            float tB = 0.03;
            float tC = 2.43;
            float tD = 0.59;
            float tE = 0.14;
            return clamp((x*(tA*x+tB))/(x*(tC*x+tD)+tE),0.0,1.0);
        }
        
        vec4 main(in float2 fragCoord ) {
        
            float AR = resolution.x/resolution.y;
            float M = 1.0; //canvas.innerWidth/M //canvas.innerHeight/M --res
            
            vec2 uvMouse = (iMouse.xy / resolution.xy);
            uvMouse.x *= AR;
            
            vec2 uv0 = (fragCoord.xy / resolution.xy);
            uv0 *= M;
            //uv0.x *= AR;
            
            vec2 uv = uv0 * (2.0*M) - (1.0*M);
            uv.x *=AR;
            
            if (uvMouse.y == 0.) uvMouse.y=(0.7-(0.05*fov)); //initial view 
            if (uvMouse.x == 0.) uvMouse.x=(1.0-(0.05*fov)); //initial view
            
            Ds = normalize(vec3(uvMouse.x-((0.5*AR)), uvMouse.y-0.5, (fov/-2.0)));
            
            vec3 O = vec3(0., cameraheight, 0.);
            vec3 D = normalize(vec3(uv, -(fov*M)));
        
            vec3 color = vec3(0.);
            
            if (D.y < -ts) {
                float L = - O.y / D.y;
                O = O + D * L;
                D.y = -D.y;
                D = normalize(D);
            }
            else{
                float L1 =  O.y / D.y;
                vec3 O1 = O + D * L1;
        
                vec3 D1 = vec3(1.);
                D1 = normalize(D);
            }
            
              float t = max(0.001, D.y) + max(-D.y, -0.001);
        
              // optical depth -> zenithAngle
              float sR = RayleighAtt / t ;
              float sM = MieAtt / t ;
        
              float cosine = clamp(dot(D,Ds),0.0,1.0);
              vec3 extinction = exp(-(_betaR * sR + _betaM * sM));
        
               // scattering phase
              float g2 = g * g;
              float fcos2 = cosine * cosine;
              float miePhase = Mie * pow(1. + g2 + 2. * g * cosine, -1.5) * (1. - g2) / (2. + g2);
                //g = 0;
              float rayleighPhase = Rayleigh;
        
              vec3 inScatter = (1. + fcos2) * vec3(rayleighPhase + _betaM / _betaR * miePhase);
        
              color = inScatter*(1.0-extinction); // *vec3(1.6,1.4,1.0)
        
                // sun
              color += 0.47*vec3(1.6,1.4,1.0)*pow( cosine, 350.0 ) * extinction;
              // sun haze
              color += 0.4*vec3(0.8,0.9,1.0)*pow( cosine, 2.0 )* extinction;
            
              color = ACESFilm(color);
            
              color = pow(color, vec3(Gamma));
            
              return vec4(color, 1.);
        }
    """
)
