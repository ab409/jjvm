package me.ygy.jjvm.instructions;

import com.sun.org.apache.bcel.internal.generic.IFNONNULL;
import me.ygy.jjvm.instructions.comparisons.*;
import me.ygy.jjvm.instructions.comparisons.ifacmp.IfACmpEq;
import me.ygy.jjvm.instructions.comparisons.ifacmp.IfACmpNe;
import me.ygy.jjvm.instructions.comparisons.ifcondition.*;
import me.ygy.jjvm.instructions.comparisons.ificmp.*;
import me.ygy.jjvm.instructions.constants.*;
import me.ygy.jjvm.instructions.control.Goto;
import me.ygy.jjvm.instructions.control.LoopupSwitch;
import me.ygy.jjvm.instructions.control.TableSwitch;
import me.ygy.jjvm.instructions.conversions.d2x.D2F;
import me.ygy.jjvm.instructions.conversions.d2x.D2I;
import me.ygy.jjvm.instructions.conversions.d2x.D2L;
import me.ygy.jjvm.instructions.conversions.f2x.F2D;
import me.ygy.jjvm.instructions.conversions.f2x.F2I;
import me.ygy.jjvm.instructions.conversions.f2x.F2L;
import me.ygy.jjvm.instructions.conversions.i2x.*;
import me.ygy.jjvm.instructions.conversions.l2x.L2D;
import me.ygy.jjvm.instructions.conversions.l2x.L2F;
import me.ygy.jjvm.instructions.conversions.l2x.L2I;
import me.ygy.jjvm.instructions.extended.GotoW;
import me.ygy.jjvm.instructions.extended.IfNonNull;
import me.ygy.jjvm.instructions.extended.IfNull;
import me.ygy.jjvm.instructions.extended.Wide;
import me.ygy.jjvm.instructions.loads.a.*;
import me.ygy.jjvm.instructions.loads.d.*;
import me.ygy.jjvm.instructions.loads.f.*;
import me.ygy.jjvm.instructions.loads.i.*;
import me.ygy.jjvm.instructions.loads.l.*;
import me.ygy.jjvm.instructions.loads.xa.*;
import me.ygy.jjvm.instructions.math.add.DAdd;
import me.ygy.jjvm.instructions.math.add.FAdd;
import me.ygy.jjvm.instructions.math.add.IAdd;
import me.ygy.jjvm.instructions.math.add.LAdd;
import me.ygy.jjvm.instructions.math.bit.*;
import me.ygy.jjvm.instructions.math.div.DDiv;
import me.ygy.jjvm.instructions.math.div.FDiv;
import me.ygy.jjvm.instructions.math.div.IDiv;
import me.ygy.jjvm.instructions.math.div.LDiv;
import me.ygy.jjvm.instructions.math.inc.IInc;
import me.ygy.jjvm.instructions.math.mul.DMul;
import me.ygy.jjvm.instructions.math.mul.FMul;
import me.ygy.jjvm.instructions.math.mul.IMul;
import me.ygy.jjvm.instructions.math.mul.LMul;
import me.ygy.jjvm.instructions.math.neg.DNeg;
import me.ygy.jjvm.instructions.math.neg.FNeg;
import me.ygy.jjvm.instructions.math.neg.INeg;
import me.ygy.jjvm.instructions.math.neg.LNeg;
import me.ygy.jjvm.instructions.math.rem.DRem;
import me.ygy.jjvm.instructions.math.rem.FRem;
import me.ygy.jjvm.instructions.math.rem.IRem;
import me.ygy.jjvm.instructions.math.rem.LRem;
import me.ygy.jjvm.instructions.math.sh.*;
import me.ygy.jjvm.instructions.math.sub.DSub;
import me.ygy.jjvm.instructions.math.sub.FSub;
import me.ygy.jjvm.instructions.math.sub.ISub;
import me.ygy.jjvm.instructions.math.sub.LSub;
import me.ygy.jjvm.instructions.references.*;
import me.ygy.jjvm.instructions.stack.*;
import me.ygy.jjvm.instructions.stores.a.*;
import me.ygy.jjvm.instructions.stores.d.*;
import me.ygy.jjvm.instructions.stores.f.*;
import me.ygy.jjvm.instructions.stores.i.*;
import me.ygy.jjvm.instructions.stores.l.*;
import me.ygy.jjvm.instructions.stores.xa.*;
import me.ygy.jjvm.rtda.Frame;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public interface Instruction {
    //todo BytecodeReader
    void fetchOperands(BytecodeReader reader);
    void execute(Frame frame);

    Nop nop = new Nop();
    AConstNull aConstNull = new AConstNull();
    IConstM1 iConstM1 = new IConstM1();
    IConst0 iConst0 = new IConst0();
    IConst1 iConst1 = new IConst1();
    IConst2 iConst2 = new IConst2();
    IConst3 iConst3 = new IConst3();
    IConst4 iConst4 = new IConst4();
    IConst5 iConst5 = new IConst5();
    LConst0 lConst0 = new LConst0();
    LConst1 lConst1 = new LConst1();
    FConst0 fConst0 = new FConst0();
    FConst1 fConst1 = new FConst1();
    FConst2 fConst2 = new FConst2();
    DConst0 dConst0 = new DConst0();
    DConst1 dConst1 = new DConst1();
    ILoad0 iLoad0 = new ILoad0();
    ILoad1 iLoad1 = new ILoad1();
    ILoad2 iLoad2 = new ILoad2();
    ILoad3 iLoad3 = new ILoad3();
    LLoad0 lLoad0 = new LLoad0();
    LLoad1 lLoad1 = new LLoad1();
    LLoad2 lLoad2 = new LLoad2();
    LLoad3 lLoad3 = new LLoad3();
    FLoad0 fLoad0 = new FLoad0();
    FLoad1 fLoad1 = new FLoad1();
    FLoad2 fLoad2 = new FLoad2();
    FLoad3 fLoad3 = new FLoad3();
    DLoad0 dLoad0 = new DLoad0();
    DLoad1 dLoad1 = new DLoad1();
    DLoad2 dLoad2 = new DLoad2();
    DLoad3 dLoad3 = new DLoad3();
    ALoad0 aLoad0 = new ALoad0();
    ALoad1 aLoad1 = new ALoad1();
    ALoad2 aLoad2 = new ALoad2();
    ALoad3 aLoad3 = new ALoad3();
    IALoad iaLoad = new IALoad();
    LALoad laLoad = new LALoad();
    FALoad faLoad = new FALoad();
    DALoad daLoad = new DALoad();
    AALoad aaLoad = new AALoad();
    BALoad baLoad = new BALoad();
    CALoad caLoad = new CALoad();
    SALoad saLoad = new SALoad();
    IStore0 iStore0 = new IStore0();
    IStore1 iStore1 = new IStore1();
    IStore2 iStore2 = new IStore2();
    IStore3 iStore3 = new IStore3();
    LStore0 lStore0 = new LStore0();
    LStore1 lStore1 = new LStore1();
    LStore2 lStore2 = new LStore2();
    LStore3 lStore3 = new LStore3();
    FStore0 fStore0 = new FStore0();
    FStore1 fStore1 = new FStore1();
    FStore2 fStore2 = new FStore2();
    FStore3 fStore3 = new FStore3();
    DStore0 dStore0 = new DStore0();
    DStore1 dStore1 = new DStore1();
    DStore2 dStore2 = new DStore2();
    DStore3 dStore3 = new DStore3();
    AStore0 aStore0 = new AStore0();
    AStore1 aStore1 = new AStore1();
    AStore2 aStore2 = new AStore2();
    AStore3 aStore3 = new AStore3();
    IAStore iaStore = new IAStore();
    LAStore laStore = new LAStore();
    FAStore faStore = new FAStore();
    DAStore daStore = new DAStore();
    AAStore aaStore = new AAStore();
    BAStore baStore = new BAStore();
    CAStore caStore = new CAStore();
    SAStore saStore = new SAStore();
    Pop pop = new Pop();
    Pop2 pop2 = new Pop2();
    Dup dup = new Dup();
    DupX1 dupX1 = new DupX1();
    DupX2 dupX2 = new DupX2();
    Dup2 dup2 = new Dup2();
    Dup2X1 dup2X1 = new Dup2X1();
    Dup2X2 dup2X2 = new Dup2X2();
    Swap swap = new Swap();
    IAdd iAdd = new IAdd();
    LAdd lAdd = new LAdd();
    FAdd fAdd = new FAdd();
    DAdd dAdd = new DAdd();
    ISub iSub = new ISub();
    LSub lSub = new LSub();
    FSub fSub = new FSub();
    DSub dSub = new DSub();
    IMul iMul = new IMul();
    LMul lMul = new LMul();
    FMul fMul = new FMul();
    DMul dMul = new DMul();
    IDiv iDiv = new IDiv();
    LDiv lDiv = new LDiv();
    FDiv fDiv = new FDiv();
    DDiv dDiv = new DDiv();
    IRem iRem = new IRem();
    LRem lRem = new LRem();
    FRem fRem = new FRem();
    DRem dRem = new DRem();
    INeg iNeg = new INeg();
    LNeg lNeg = new LNeg();
    FNeg fNeg = new FNeg();
    DNeg dNeg = new DNeg();
    IShL iShl = new IShL();
    LShL lShl = new LShL();
    IShR iShr = new IShR();
    LShR lShr = new LShR();
    IUShR iUshr = new IUShR();
    LUShR lUshr = new LUShR();
    IAnd iAnd = new IAnd();
    LAnd lAnd = new LAnd();
    IOr iOr = new IOr();
    LOr lOr = new LOr();
    IXor iXor = new IXor();
    LXor lXor = new LXor();
    I2L i2l = new I2L();
    I2F i2f = new I2F();
    I2D i2d = new I2D();
    L2I l2i = new L2I();
    L2F l2f = new L2F();
    L2D l2d = new L2D();
    F2I f2i = new F2I();
    F2L f2l = new F2L();
    F2D f2d = new F2D();
    D2I d2i = new D2I();
    D2L d2l = new D2L();
    D2F d2f = new D2F();
    I2B i2b = new I2B();
    I2C i2c = new I2C();
    I2S i2s = new I2S();
    LCmp lCmp = new LCmp();
    FCmpl fCmpl = new FCmpl();
    FCmpg fCmpg = new FCmpg();
    DCmpl dCmpl = new DCmpl();
    DCmpg dCmpg = new DCmpg();



    static Instruction newInstruction(int opcode) {
        switch (opcode) {
            case 0x00:
                return nop;
            case 0x01:
                return aConstNull;
            case 0x02:
                return iConstM1;
            case 0x03:
                return iConst0;
            case 0x04:
                return iConst1;
            case 0x05:
                return iConst2;
            case 0x06:
                return iConst3;
            case 0x07:
                return iConst4;
            case 0x08:
                return iConst5;
            case 0x09:
                return lConst0;
            case 0x0a:
                return lConst1;
            case 0x0b:
                return fConst0;
            case 0x0c:
                return fConst1;
            case 0x0d:
                return fConst2;
            case 0x0e:
                return dConst0;
            case 0x0f:
                return dConst1;
            case 0x10:
                return new BIPush();
            case 0x11:
                return new SIPush();
            case 0x12:
                //todo ldc
                throw new IllegalArgumentException("ldc not implement");
            case 0x13:
                //todo ldc
                throw new IllegalArgumentException("ldc not implement");
            case 0x14:
                //todo ldc
                throw new IllegalArgumentException("ldc not implement");
            case 0x15:
                return new ILoad();
            case 0x16:
                return new LLoad();
            case 0x17:
                return new FLoad();
            case 0x18:
                return new DLoad();
            case 0x19:
                return new ALoad();
            case 0x1a:
                return iLoad0;
            case 0x1b:
                return iLoad1;
            case 0x1c:
                return iLoad2;
            case 0x1d:
                return iLoad3;
            case 0x1e:
                return lLoad0;
            case 0x1f:
                return lLoad1;
            case 0x20:
                return lLoad2;
            case 0x21:
                return lLoad3;
            case 0x22:
                return fLoad0;
            case 0x23:
                return fLoad1;
            case 0x24:
                return fLoad2;
            case 0x25:
                return fLoad3;
            case 0x26:
                return dLoad0;
            case 0x27:
                return dLoad1;
            case 0x28:
                return dLoad2;
            case 0x29:
                return dLoad3;
            case 0x2a:
                return aLoad0;
            case 0x2b:
                return aLoad1;
            case 0x2c:
                return aLoad2;
            case 0x2d:
                return aLoad3;
            case 0x2e:
                return iaLoad;
            case 0x2f:
                return laLoad;
            case 0x30:
                return faLoad;
            case 0x31:
                return daLoad;
            case 0x32:
                return aaLoad;
            case 0x33:
                return baLoad;
            case 0x34:
                return caLoad;
            case 0x35:
                return saLoad;
            case 0x36:
                return new IStore();
            case 0x37:
                return new LStore();
            case 0x38:
                return new FStore();
            case 0x39:
                return new DStore();
            case 0x3a:
                return new AStore();
            case 0x3b:
                return iStore0;
            case 0x3c:
                return iStore1;
            case 0x3d:
                return iStore2;
            case 0x3e:
                return iStore3;
            case 0x3f:
                return lStore0;
            case 0x40:
                return lStore1;
            case 0x41:
                return lStore2;
            case 0x42:
                return lStore3;
            case 0x43:
                return fStore0;
            case 0x44:
                return fStore1;
            case 0x45:
                return fStore2;
            case 0x46:
                return fStore3;
            case 0x47:
                return dStore0;
            case 0x48:
                return dStore1;
            case 0x49:
                return dStore2;
            case 0x4a:
                return dStore3;
            case 0x4b:
                return aStore0;
            case 0x4c:
                return aStore1;
            case 0x4d:
                return aStore2;
            case 0x4e:
                return aStore3;
            case 0x4f:
                return iaStore;
            case 0x50:
                return laStore;
            case 0x51:
                return faStore;
            case 0x52:
                return daStore;
            case 0x53:
                return aaStore;
            case 0x54:
                return baStore;
            case 0x55:
                return caStore;
            case 0x56:
                return saStore;
            case 0x57:
                return pop;
            case 0x58:
                return pop2;
            case 0x59:
                return dup;
            case 0x5a:
                return dupX1;
            case 0x5b:
                return dupX2;
            case 0x5c:
                return dup2;
            case 0x5d:
                return dup2X1;
            case 0x5e:
                return dup2X2;
            case 0x5f:
                return swap;
            case 0x60:
                return iAdd;
            case 0x61:
                return lAdd;
            case 0x62:
                return fAdd;
            case 0x63:
                return dAdd;
            case 0x64:
                return iSub;
            case 0x65:
                return lSub;
            case 0x66:
                return fSub;
            case 0x67:
                return dSub;
            case 0x68:
                return iMul;
            case 0x69:
                return lMul;
            case 0x6a:
                return fMul;
            case 0x70:
                return iRem;
            case 0x71:
                return lRem;
            case 0x72:
                return fRem;
            case 0x73:
                return dRem;
            case 0x74:
                return iNeg;
            case 0x75:
                return lNeg;
            case 0x76:
                return fNeg;
            case 0x77:
                return dNeg;
            case 0x78:
                return iShl;
            case 0x79:
                return lShl;
            case 0x7a:
                return iShr;
            case 0x7b:
                return lShr;
            case 0x7c:
                return iUshr;
            case 0x7d:
                return lUshr;
            case 0x7e:
                return iAnd;
            case 0x7f:
                return lAnd;
            case 0x80:
                return iOr;
            case 0x81:
                return lOr;
            case 0x82:
                return iXor;
            case 0x83:
                return lXor;
            case 0x84:
                return new IInc();
            case 0x85:
                return i2l;
            case 0x86:
                return i2f;
            case 0x87:
                return i2d;
            case 0x88:
                return l2i;
            case 0x89:
                return l2f;
            case 0x8a:
                return l2d;
            case 0x8b:
                return f2i;
            case 0x8c:
                return f2l;
            case 0x8d:
                return f2d;
            case 0x8e:
                return d2i;
            case 0x8f:
                return d2l;
            case 0x90:
                return d2f;
            case 0x91:
                return i2b;
            case 0x92:
                return i2c;
            case 0x93:
                return i2s;
            case 0x94:
                return lCmp;
            case 0x95:
                return fCmpl;
            case 0x96:
                return fCmpg;
            case 0x97:
                return dCmpl;
            case 0x98:
                return dCmpg;
            case 0x99:
                return new IfEq();
            case 0x9a:
                return new IfNe();
            case 0x9b:
                return new IfLt();
            case 0x9c:
                return new IfGe();
            case 0x9d:
                return new IfGt();
            case 0x9e:
                return new IfLe();
            case 0x9f:
                return new IfICmpEq();
            case 0xa0:
                return new IfICmpNe();
            case 0xa1:
                return new IfICmpLt();
            case 0xa2:
                return new IfICmpGe();
            case 0xa3:
                return new IfICmpGt();
            case 0xa4:
                return new IfICmpLe();
            case 0xa5:
                return new IfACmpEq();
            case 0xa6:
                return new IfACmpNe();
            case 0xa7:
                return new Goto();
            case 0xa8:
                //todo jsr
                throw new IllegalArgumentException("jsr not implement");
            case 0xa9:
                //todo ret
                throw new IllegalArgumentException("ret not implement");
            case 0xaa:
                return new TableSwitch();
            case 0xab:
                return new LoopupSwitch();
            case 0xac:
                //todo ireturn
                throw new IllegalArgumentException("ireturn not implement");
            case 0xad:
                //todo lreturn
                throw new IllegalArgumentException("lreturn not implement");
            case 0xae:
                //todo freturn
                throw new IllegalArgumentException("freturn not implement");
            case 0xaf:
                //todo dreturn
                throw new IllegalArgumentException("dreturn not implement");
            case 0xb0:
                //todo areturn
                throw new IllegalArgumentException("areturn not implement");
            case 0xb1:
                //todo _return
                throw new IllegalArgumentException("_return not implement");
            case 0xb2:
                //GET_STATIC
//                throw new IllegalArgumentException("GET_STATIC not implement");
                return new GetStatic();
            case 0xb3:
                //PUT_STATIC
//                throw new IllegalArgumentException("PUT_STATIC not implement");
                return new PutStatic();
            case 0xb4:
                //GET_FIELD
//                throw new IllegalArgumentException("GET_FIELD not implement");
                return new GetField();
            case 0xb5:
                //PUT_FIELD
//                throw new IllegalArgumentException("PUT_FIELD not implement");
                return new PutField();
            case 0xb6:
                //todo INVOKE_VIRTUAL
//                throw new IllegalArgumentException("INVOKE_VIRTUAL not implement");
                return new InvokeVirtual();
            case 0xb7:
                //todo INVOKE_SPECIAL
//                throw new IllegalArgumentException("INVOKE_SPECIAL not implement");
                return new InvokeSpecial();
            case 0xb8:
                //todo INVOKE_STATIC
                throw new IllegalArgumentException("INVOKE_STATIC not implement");
            case 0xb9:
                //todo INVOKE_INTERFACE
                throw new IllegalArgumentException("INVOKE_INTERFACE not implement");
            case 0xba:
                //todo INVOKE_DYNAMIC
                throw new IllegalArgumentException("INVOKE_DYNAMIC not implement");
            case 0xbb:
                //NEW
//                throw new IllegalArgumentException("NEW not implement");
                return new New();
            case 0xbc:
                //todo NEW_ARRAY
                throw new IllegalArgumentException("NEW_ARRAY not implement");
            case 0xbd:
                //todo ANEW_ARRAY
                throw new IllegalArgumentException("ANEW_ARRAY not implement");
            case 0xbe:
                //todo arraylength
                throw new IllegalArgumentException("arraylength not implement");
            case 0xbf:
                //todo athrow
                throw new IllegalArgumentException("athrow not implement");
            case 0xc0:
                //CHECK_CAST
//                throw new IllegalArgumentException("CHECK_CAST not implement");
                return new CheckCast();
            case 0xc1:
                //INSTANCE_OF
//                throw new IllegalArgumentException("INSTANCE_OF not implement");
                return new Instanceof();
            case 0xc2:
                //todo monitorenter
                throw new IllegalArgumentException("monitorenter not implement");
            case 0xc3:
                //todo monitorexit
                throw new IllegalArgumentException("monitorexit not implement");
            case 0xc4:
                return new Wide();
            case 0xc5:
                //todo MULTI_ANEW_ARRAY
                throw new IllegalArgumentException("MULTI_ANEW_ARRAY not implement");
            case 0xc6:
                return new IfNull();
            case 0xc7:
                return new IfNonNull();
            case 0xc8:
                return new GotoW();
            case 0xc9:
                //todo JSR_W
                throw new IllegalArgumentException("JSR_W not implement");
            case 0xfe:
                //todo invoke_native
                throw new IllegalArgumentException("invoke_native not implement");
            case 0xff:
                //todo BOOTSTRAP
                throw new IllegalArgumentException("BOOTSTRAP not implement");
            default:
                throw new IllegalArgumentException("instruction not implement, opcode="+opcode);
        }
    }
}
