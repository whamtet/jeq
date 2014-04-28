/*
 *  21.04.2004 Original verion. davagin@udm.ru.
 *-----------------------------------------------------------------------
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *----------------------------------------------------------------------
 */
package davaguine.jeq.core;

/**
 * Author: Dmitry Vaguine
 * Date: 02.05.2004
 * Time: 12:00:29
 */
public class IIRBase {
    /* BETA, ALPHA, GAMMA */
    public final static IIRCoefficients iir_cf10_11k_11025[] = {
            /* 31 Hz*/
            new IIRCoefficients(9.8758524689e-01, 6.2073765555e-03, 1.9872750693e+00),
            /* 62 Hz*/
            new IIRCoefficients(9.7532461998e-01, 1.2337690008e-02, 1.9740916593e+00),
            /* 125 Hz*/
            new IIRCoefficients(9.5087485437e-01, 2.4562572817e-02, 1.9459267562e+00),
            /* 250 Hz*/
            new IIRCoefficients(9.0416308662e-01, 4.7918456688e-02, 1.8848691023e+00),
            /* 500 Hz*/
            new IIRCoefficients(8.1751373987e-01, 9.1243130064e-02, 1.7442229115e+00),
            /* 1k Hz*/
            new IIRCoefficients(6.6840529852e-01, 1.6579735074e-01, 1.4047189863e+00),
            /* 2k Hz*/
            new IIRCoefficients(4.4858358977e-01, 2.7570820511e-01, 6.0517475334e-01),
            /* 3k Hz*/
            new IIRCoefficients(3.1012671838e-01, 3.4493664081e-01, -1.8141012760e-01),
            /* 4k Hz*/
            new IIRCoefficients(2.4198119087e-01, 3.7900940457e-01, -8.0845085113e-01),
            /* 5.5k Hz*/
            new IIRCoefficients(3.3453245058e-01, 3.3273377471e-01, -1.3344985880e+00)
    };

    public final static IIRCoefficients iir_cf10_22k_22050[] = {
            /* 31 Hz*/
            new IIRCoefficients(9.9377323686e-01, 3.1133815717e-03, 1.9936954495e+00),
            /* 62 Hz*/
            new IIRCoefficients(9.8758524689e-01, 6.2073765555e-03, 1.9872750693e+00),
            /* 125 Hz*/
            new IIRCoefficients(9.7512812040e-01, 1.2435939802e-02, 1.9738753198e+00),
            /* 250 Hz*/
            new IIRCoefficients(9.5087485437e-01, 2.4562572817e-02, 1.9459267562e+00),
            /* 500 Hz*/
            new IIRCoefficients(9.0416308662e-01, 4.7918456688e-02, 1.8848691023e+00),
            /* 1k Hz*/
            new IIRCoefficients(8.1751373987e-01, 9.1243130064e-02, 1.7442229115e+00),
            /* 2k Hz*/
            new IIRCoefficients(6.6840529852e-01, 1.6579735074e-01, 1.4047189863e+00),
            /* 4k Hz*/
            new IIRCoefficients(4.4858358977e-01, 2.7570820511e-01, 6.0517475334e-01),
            /* 8k Hz*/
            new IIRCoefficients(2.4198119087e-01, 3.7900940457e-01, -8.0845085113e-01),
            /* 11k Hz*/
            new IIRCoefficients(3.3453245058e-01, 3.3273377471e-01, -1.3344985880e+00)
    };
    public final static IIRCoefficients iir_cf10_44100[] = {
            /* 31 Hz*/
            new IIRCoefficients(9.9688176273e-01, 1.5591186337e-03, 1.9968622855e+00),
            /* 62 Hz*/
            new IIRCoefficients(9.9377323686e-01, 3.1133815717e-03, 1.9936954495e+00),
            /* 125 Hz*/
            new IIRCoefficients(9.8748575691e-01, 6.2571215431e-03, 1.9871705722e+00),
            /* 250 Hz*/
            new IIRCoefficients(9.7512812040e-01, 1.2435939802e-02, 1.9738753198e+00),
            /* 500 Hz*/
            new IIRCoefficients(9.5087485437e-01, 2.4562572817e-02, 1.9459267562e+00),
            /* 1k Hz*/
            new IIRCoefficients(9.0416308662e-01, 4.7918456688e-02, 1.8848691023e+00),
            /* 2k Hz*/
            new IIRCoefficients(8.1751373987e-01, 9.1243130064e-02, 1.7442229115e+00),
            /* 4k Hz*/
            new IIRCoefficients(6.6840529852e-01, 1.6579735074e-01, 1.4047189863e+00),
            /* 8k Hz*/
            new IIRCoefficients(4.4858358977e-01, 2.7570820511e-01, 6.0517475334e-01),
            /* 16k Hz*/
            new IIRCoefficients(2.4198119087e-01, 3.7900940457e-01, -8.0845085113e-01)
    };
    public final static IIRCoefficients iir_cf10_48000[] = {
            /* 31 Hz*/
            new IIRCoefficients(9.9713475915e-01, 1.4326204244e-03, 1.9971183163e+00),
            /* 62 Hz*/
            new IIRCoefficients(9.9427771143e-01, 2.8611442874e-03, 1.9942120343e+00),
            /* 125 Hz*/
            new IIRCoefficients(9.8849666727e-01, 5.7516663664e-03, 1.9882304829e+00),
            /* 250 Hz*/
            new IIRCoefficients(9.7712566171e-01, 1.1437169144e-02, 1.9760670839e+00),
            /* 500 Hz*/
            new IIRCoefficients(9.5477456091e-01, 2.2612719547e-02, 1.9505892385e+00),
            /* 1k Hz*/
            new IIRCoefficients(9.1159452679e-01, 4.4202736607e-02, 1.8952405706e+00),
            /* 2k Hz*/
            new IIRCoefficients(8.3100647694e-01, 8.4496761532e-02, 1.7686164442e+00),
            /* 4k Hz*/
            new IIRCoefficients(6.9062328809e-01, 1.5468835596e-01, 1.4641227157e+00),
            /* 8k Hz*/
            new IIRCoefficients(4.7820368352e-01, 2.6089815824e-01, 7.3910184176e-01),
            /* 16k Hz*/
            new IIRCoefficients(2.5620076154e-01, 3.7189961923e-01, -6.2810038077e-01)
    };
    public final static IIRCoefficients iir_cf15_44100[] = {
            /* 25 Hz*/
            new IIRCoefficients(9.9834072702e-01, 8.2963648917e-04, 1.9983280505e+00),
            /* 40 Hz*/
            new IIRCoefficients(9.9734652663e-01, 1.3267366865e-03, 1.9973140908e+00),
            /* 63 Hz*/
            new IIRCoefficients(9.9582396353e-01, 2.0880182333e-03, 1.9957435641e+00),
            /* 100 Hz*/
            new IIRCoefficients(9.9337951306e-01, 3.3102434709e-03, 1.9931771947e+00),
            /* 160 Hz*/
            new IIRCoefficients(9.8942832039e-01, 5.2858398053e-03, 1.9889114258e+00),
            /* 250 Hz*/
            new IIRCoefficients(9.8353109588e-01, 8.2344520610e-03, 1.9822729654e+00),
            /* 400 Hz*/
            new IIRCoefficients(9.7378088082e-01, 1.3109559588e-02, 1.9705764276e+00),
            /* 630 Hz*/
            new IIRCoefficients(9.5901979676e-01, 2.0490101620e-02, 1.9511333590e+00),
            /* 1k Hz*/
            new IIRCoefficients(9.3574903986e-01, 3.2125480071e-02, 1.9161350100e+00),
            /* 1.6k Hz*/
            new IIRCoefficients(8.9923630641e-01, 5.0381846793e-02, 1.8501014162e+00),
            /* 2.5k Hz*/
            new IIRCoefficients(8.4722457681e-01, 7.6387711593e-02, 1.7312785699e+00),
            /* 4k Hz*/
            new IIRCoefficients(7.6755471307e-01, 1.1622264346e-01, 1.4881981417e+00),
            /* 6.3k Hz*/
            new IIRCoefficients(6.6125377473e-01, 1.6937311263e-01, 1.0357747868e+00),
            /* 10k Hz*/
            new IIRCoefficients(5.2683267950e-01, 2.3658366025e-01, 2.2218349322e-01),
            /* 16k Hz*/
            new IIRCoefficients(4.0179628792e-01, 2.9910185604e-01, -9.1248032613e-01)
    };
    public final static IIRCoefficients iir_cf15_48000[] = {
            /* 25 Hz*/
            new IIRCoefficients(9.9847546664e-01, 7.6226668143e-04, 1.9984647656e+00),
            /* 40 Hz*/
            new IIRCoefficients(9.9756184654e-01, 1.2190767289e-03, 1.9975344645e+00),
            /* 63 Hz*/
            new IIRCoefficients(9.9616261379e-01, 1.9186931041e-03, 1.9960947369e+00),
            /* 100 Hz*/
            new IIRCoefficients(9.9391578543e-01, 3.0421072865e-03, 1.9937449618e+00),
            /* 160 Hz*/
            new IIRCoefficients(9.9028307215e-01, 4.8584639242e-03, 1.9898465702e+00),
            /* 250 Hz*/
            new IIRCoefficients(9.8485897264e-01, 7.5705136795e-03, 1.9837962543e+00),
            /* 400 Hz*/
            new IIRCoefficients(9.7588512657e-01, 1.2057436715e-02, 1.9731772447e+00),
            /* 630 Hz*/
            new IIRCoefficients(9.6228521814e-01, 1.8857390928e-02, 1.9556164694e+00),
            /* 1k Hz*/
            new IIRCoefficients(9.4080933132e-01, 2.9595334338e-02, 1.9242054384e+00),
            /* 1.6k Hz*/
            new IIRCoefficients(9.0702059196e-01, 4.6489704022e-02, 1.8653476166e+00),
            /* 2.5k Hz*/
            new IIRCoefficients(8.5868004289e-01, 7.0659978553e-02, 1.7600401337e+00),
            /* 4k Hz*/
            new IIRCoefficients(7.8409610788e-01, 1.0795194606e-01, 1.5450725522e+00),
            /* 6.3k Hz*/
            new IIRCoefficients(6.8332861002e-01, 1.5833569499e-01, 1.1426447155e+00),
            /* 10k Hz*/
            new IIRCoefficients(5.5267518228e-01, 2.2366240886e-01, 4.0186190803e-01),
            /* 16k Hz*/
            new IIRCoefficients(4.1811888447e-01, 2.9094055777e-01, -7.0905944223e-01)
    };
    public final static IIRCoefficients iir_cf25_44100[] = {
            /* 20 Hz*/
            new IIRCoefficients(9.9934037157e-01, 3.2981421662e-04, 1.9993322545e+00),
            /* 31.5 Hz*/
            new IIRCoefficients(9.9896129025e-01, 5.1935487310e-04, 1.9989411587e+00),
            /* 40 Hz*/
            new IIRCoefficients(9.9868118265e-01, 6.5940867495e-04, 1.9986487252e+00),
            /* 50 Hz*/
            new IIRCoefficients(9.9835175161e-01, 8.2412419683e-04, 1.9983010452e+00),
            /* 80 Hz*/
            new IIRCoefficients(9.9736411067e-01, 1.3179446674e-03, 1.9972343673e+00),
            /* 100 Hz*/
            new IIRCoefficients(9.9670622662e-01, 1.6468866919e-03, 1.9965035707e+00),
            /* 125 Hz*/
            new IIRCoefficients(9.9588448566e-01, 2.0577571681e-03, 1.9955679690e+00),
            /* 160 Hz*/
            new IIRCoefficients(9.9473519326e-01, 2.6324033689e-03, 1.9942169198e+00),
            /* 250 Hz*/
            new IIRCoefficients(9.9178600786e-01, 4.1069960678e-03, 1.9905226414e+00),
            /* 315 Hz*/
            new IIRCoefficients(9.8966154150e-01, 5.1692292513e-03, 1.9876580847e+00),
            /* 400 Hz*/
            new IIRCoefficients(9.8689036168e-01, 6.5548191616e-03, 1.9836646251e+00),
            /* 500 Hz*/
            new IIRCoefficients(9.8364027156e-01, 8.1798642207e-03, 1.9786090689e+00),
            /* 800 Hz*/
            new IIRCoefficients(9.7395577681e-01, 1.3022111597e-02, 1.9611472340e+00),
            /* 1k Hz*/
            new IIRCoefficients(9.6755437936e-01, 1.6222810321e-02, 1.9476180811e+00),
            /* 1.25k Hz*/
            new IIRCoefficients(9.5961458750e-01, 2.0192706249e-02, 1.9286193446e+00),
            /* 1.6k Hz*/
            new IIRCoefficients(9.4861481164e-01, 2.5692594182e-02, 1.8982024567e+00),
            /* 2.5k Hz*/
            new IIRCoefficients(9.2095325455e-01, 3.9523372724e-02, 1.8003794694e+00),
            /* 3.15k Hz*/
            new IIRCoefficients(9.0153642498e-01, 4.9231787512e-02, 1.7132251201e+00),
            /* 4k Hz*/
            new IIRCoefficients(8.7685876255e-01, 6.1570618727e-02, 1.5802270232e+00),
            /* 5k Hz*/
            new IIRCoefficients(8.4886734822e-01, 7.5566325889e-02, 1.3992391376e+00),
            /* 8k Hz*/
            new IIRCoefficients(7.7175298860e-01, 1.1412350570e-01, 7.4018523020e-01),
            /* 10k Hz*/
            new IIRCoefficients(7.2627049462e-01, 1.3686475269e-01, 2.5120552756e-01),
            /* 12.5k Hz*/
            new IIRCoefficients(6.7674787974e-01, 1.6162606013e-01, -3.4978377639e-01),
            /* 16k Hz*/
            new IIRCoefficients(6.2482197550e-01, 1.8758901225e-01, -1.0576558797e+00),
            /* 20k Hz*/
            new IIRCoefficients(6.1776148240e-01, 1.9111925880e-01, -1.5492465594e+00)
    };
    public final static IIRCoefficients iir_cf25_48000[] = {
            /* 20 Hz*/
            new IIRCoefficients(9.9939388451e-01, 3.0305774630e-04, 1.9993870327e+00),
            /* 31.5 Hz*/
            new IIRCoefficients(9.9904564663e-01, 4.7717668529e-04, 1.9990286528e+00),
            /* 40 Hz*/
            new IIRCoefficients(9.9878827195e-01, 6.0586402557e-04, 1.9987608731e+00),
            /* 50 Hz*/
            new IIRCoefficients(9.9848556942e-01, 7.5721528829e-04, 1.9984427652e+00),
            /* 80 Hz*/
            new IIRCoefficients(9.9757801538e-01, 1.2109923088e-03, 1.9974684869e+00),
            /* 100 Hz*/
            new IIRCoefficients(9.9697343933e-01, 1.5132803374e-03, 1.9968023538e+00),
            /* 125 Hz*/
            new IIRCoefficients(9.9621823598e-01, 1.8908820086e-03, 1.9959510180e+00),
            /* 160 Hz*/
            new IIRCoefficients(9.9516191728e-01, 2.4190413595e-03, 1.9947243453e+00),
            /* 250 Hz*/
            new IIRCoefficients(9.9245085008e-01, 3.7745749576e-03, 1.9913840669e+00),
            /* 315 Hz*/
            new IIRCoefficients(9.9049749914e-01, 4.7512504310e-03, 1.9888056233e+00),
            /* 400 Hz*/
            new IIRCoefficients(9.8794899744e-01, 6.0255012789e-03, 1.9852245824e+00),
            /* 500 Hz*/
            new IIRCoefficients(9.8495930023e-01, 7.5203498850e-03, 1.9807093500e+00),
            /* 800 Hz*/
            new IIRCoefficients(9.7604570090e-01, 1.1977149551e-02, 1.9652207158e+00),
            /* 1k Hz*/
            new IIRCoefficients(9.7014963927e-01, 1.4925180364e-02, 1.9532947360e+00),
            /* 1.25k Hz*/
            new IIRCoefficients(9.6283181641e-01, 1.8584091793e-02, 1.9366149237e+00),
            /* 1.6k Hz*/
            new IIRCoefficients(9.5268463224e-01, 2.3657683878e-02, 1.9100137880e+00),
            /* 2.5k Hz*/
            new IIRCoefficients(9.2711765003e-01, 3.6441174983e-02, 1.8248457659e+00),
            /* 3.15k Hz*/
            new IIRCoefficients(9.0912548757e-01, 4.5437256213e-02, 1.7491177803e+00),
            /* 4k Hz*/
            new IIRCoefficients(8.8619860800e-01, 5.6900696000e-02, 1.6334959111e+00),
            /* 5k Hz*/
            new IIRCoefficients(8.6010264114e-01, 6.9948679430e-02, 1.4757186436e+00),
            /* 8k Hz*/
            new IIRCoefficients(7.8757448309e-01, 1.0621275845e-01, 8.9378724155e-01),
            /* 10k Hz*/
            new IIRCoefficients(7.4415362476e-01, 1.2792318762e-01, 4.5142017567e-01),
            /* 12.5k Hz*/
            new IIRCoefficients(6.9581428034e-01, 1.5209285983e-01, -1.1091156053e-01),
            /* 16k Hz*/
            new IIRCoefficients(6.4120506488e-01, 1.7939746756e-01, -8.2060253244e-01),
            /* 20k Hz*/
            new IIRCoefficients(6.0884213704e-01, 1.9557893148e-01, -1.3932981614e+00)
    };
    public final static IIRCoefficients iir_cf31_44100[] = {
            /* 20 Hz*/
            new IIRCoefficients(9.9934037157e-01, 3.2981421662e-04, 1.9993322545e+00),
            /* 25 Hz*/
            new IIRCoefficients(9.9917555233e-01, 4.1222383516e-04, 1.9991628705e+00),
            /* 31.5 Hz*/
            new IIRCoefficients(9.9896129025e-01, 5.1935487310e-04, 1.9989411587e+00),
            /* 40 Hz*/
            new IIRCoefficients(9.9868118265e-01, 6.5940867495e-04, 1.9986487252e+00),
            /* 50 Hz*/
            new IIRCoefficients(9.9835175161e-01, 8.2412419683e-04, 1.9983010452e+00),
            /* 63 Hz*/
            new IIRCoefficients(9.9792365217e-01, 1.0381739160e-03, 1.9978431682e+00),
            /* 80 Hz*/
            new IIRCoefficients(9.9736411067e-01, 1.3179446674e-03, 1.9972343673e+00),
            /* 100 Hz*/
            new IIRCoefficients(9.9670622662e-01, 1.6468866919e-03, 1.9965035707e+00),
            /* 125 Hz*/
            new IIRCoefficients(9.9588448566e-01, 2.0577571681e-03, 1.9955679690e+00),
            /* 160 Hz*/
            new IIRCoefficients(9.9473519326e-01, 2.6324033689e-03, 1.9942169198e+00),
            /* 200 Hz*/
            new IIRCoefficients(9.9342335280e-01, 3.2883236020e-03, 1.9926141028e+00),
            /* 250 Hz*/
            new IIRCoefficients(9.9178600786e-01, 4.1069960678e-03, 1.9905226414e+00),
            /* 315 Hz*/
            new IIRCoefficients(9.8966154150e-01, 5.1692292513e-03, 1.9876580847e+00),
            /* 400 Hz*/
            new IIRCoefficients(9.8689036168e-01, 6.5548191616e-03, 1.9836646251e+00),
            /* 500 Hz*/
            new IIRCoefficients(9.8364027156e-01, 8.1798642207e-03, 1.9786090689e+00),
            /* 630 Hz*/
            new IIRCoefficients(9.7943153305e-01, 1.0284233476e-02, 1.9714629236e+00),
            /* 800 Hz*/
            new IIRCoefficients(9.7395577681e-01, 1.3022111597e-02, 1.9611472340e+00),
            /* 1k Hz*/
            new IIRCoefficients(9.6755437936e-01, 1.6222810321e-02, 1.9476180811e+00),
            /* 1.25k Hz*/
            new IIRCoefficients(9.5961458750e-01, 2.0192706249e-02, 1.9286193446e+00),
            /* 1.6k Hz*/
            new IIRCoefficients(9.4861481164e-01, 2.5692594182e-02, 1.8982024567e+00),
            /* 2k Hz*/
            new IIRCoefficients(9.3620971896e-01, 3.1895140519e-02, 1.8581325022e+00),
            /* 2.5k Hz*/
            new IIRCoefficients(9.2095325455e-01, 3.9523372724e-02, 1.8003794694e+00),
            /* 3.15k Hz*/
            new IIRCoefficients(9.0153642498e-01, 4.9231787512e-02, 1.7132251201e+00),
            /* 4k Hz*/
            new IIRCoefficients(8.7685876255e-01, 6.1570618727e-02, 1.5802270232e+00),
            /* 5k Hz*/
            new IIRCoefficients(8.4886734822e-01, 7.5566325889e-02, 1.3992391376e+00),
            /* 6.3k Hz*/
            new IIRCoefficients(8.1417575446e-01, 9.2912122771e-02, 1.1311200817e+00),
            /* 8k Hz*/
            new IIRCoefficients(7.7175298860e-01, 1.1412350570e-01, 7.4018523020e-01),
            /* 10k Hz*/
            new IIRCoefficients(7.2627049462e-01, 1.3686475269e-01, 2.5120552756e-01),
            /* 12.5k Hz*/
            new IIRCoefficients(6.7674787974e-01, 1.6162606013e-01, -3.4978377639e-01),
            /* 16k Hz*/
            new IIRCoefficients(6.2482197550e-01, 1.8758901225e-01, -1.0576558797e+00),
            /* 20k Hz*/
            new IIRCoefficients(6.1776148240e-01, 1.9111925880e-01, -1.5492465594e+00)
    };
    public final static IIRCoefficients iir_cf31_48000[] = {
            /* 20 Hz*/
            new IIRCoefficients(9.9939388451e-01, 3.0305774630e-04, 1.9993870327e+00),
            /* 25 Hz*/
            new IIRCoefficients(9.9924247917e-01, 3.7876041632e-04, 1.9992317740e+00),
            /* 31.5 Hz*/
            new IIRCoefficients(9.9904564663e-01, 4.7717668529e-04, 1.9990286528e+00),
            /* 40 Hz*/
            new IIRCoefficients(9.9878827195e-01, 6.0586402557e-04, 1.9987608731e+00),
            /* 50 Hz*/
            new IIRCoefficients(9.9848556942e-01, 7.5721528829e-04, 1.9984427652e+00),
            /* 63 Hz*/
            new IIRCoefficients(9.9809219264e-01, 9.5390367779e-04, 1.9980242502e+00),
            /* 80 Hz*/
            new IIRCoefficients(9.9757801538e-01, 1.2109923088e-03, 1.9974684869e+00),
            /* 100 Hz*/
            new IIRCoefficients(9.9697343933e-01, 1.5132803374e-03, 1.9968023538e+00),
            /* 125 Hz*/
            new IIRCoefficients(9.9621823598e-01, 1.8908820086e-03, 1.9959510180e+00),
            /* 160 Hz*/
            new IIRCoefficients(9.9516191728e-01, 2.4190413595e-03, 1.9947243453e+00),
            /* 200 Hz*/
            new IIRCoefficients(9.9395607757e-01, 3.0219612131e-03, 1.9932727986e+00),
            /* 250 Hz*/
            new IIRCoefficients(9.9245085008e-01, 3.7745749576e-03, 1.9913840669e+00),
            /* 315 Hz*/
            new IIRCoefficients(9.9049749914e-01, 4.7512504310e-03, 1.9888056233e+00),
            /* 400 Hz*/
            new IIRCoefficients(9.8794899744e-01, 6.0255012789e-03, 1.9852245824e+00),
            /* 500 Hz*/
            new IIRCoefficients(9.8495930023e-01, 7.5203498850e-03, 1.9807093500e+00),
            /* 630 Hz*/
            new IIRCoefficients(9.8108651246e-01, 9.4567437704e-03, 1.9743538683e+00),
            /* 800 Hz*/
            new IIRCoefficients(9.7604570090e-01, 1.1977149551e-02, 1.9652207158e+00),
            /* 1k Hz*/
            new IIRCoefficients(9.7014963927e-01, 1.4925180364e-02, 1.9532947360e+00),
            /* 1.25k Hz*/
            new IIRCoefficients(9.6283181641e-01, 1.8584091793e-02, 1.9366149237e+00),
            /* 1.6k Hz*/
            new IIRCoefficients(9.5268463224e-01, 2.3657683878e-02, 1.9100137880e+00),
            /* 2k Hz*/
            new IIRCoefficients(9.4122788957e-01, 2.9386055213e-02, 1.8750821533e+00),
            /* 2.5k Hz*/
            new IIRCoefficients(9.2711765003e-01, 3.6441174983e-02, 1.8248457659e+00),
            /* 3.15k Hz*/
            new IIRCoefficients(9.0912548757e-01, 4.5437256213e-02, 1.7491177803e+00),
            /* 4k Hz*/
            new IIRCoefficients(8.8619860800e-01, 5.6900696000e-02, 1.6334959111e+00),
            /* 5k Hz*/
            new IIRCoefficients(8.6010264114e-01, 6.9948679430e-02, 1.4757186436e+00),
            /* 6.3k Hz*/
            new IIRCoefficients(8.2760520925e-01, 8.6197395374e-02, 1.2405797786e+00),
            /* 8k Hz*/
            new IIRCoefficients(7.8757448309e-01, 1.0621275845e-01, 8.9378724155e-01),
            /* 10k Hz*/
            new IIRCoefficients(7.4415362476e-01, 1.2792318762e-01, 4.5142017567e-01),
            /* 12.5k Hz*/
            new IIRCoefficients(6.9581428034e-01, 1.5209285983e-01, -1.1091156053e-01),
            /* 16k Hz*/
            new IIRCoefficients(6.4120506488e-01, 1.7939746756e-01, -8.2060253244e-01),
            /* 20k Hz*/
            new IIRCoefficients(6.0884213704e-01, 1.9557893148e-01, -1.3932981614e+00),
    };
}
