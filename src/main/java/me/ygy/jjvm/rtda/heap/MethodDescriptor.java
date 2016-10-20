package me.ygy.jjvm.rtda.heap;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guangyuanyu on 2016/10/19.
 */
public class MethodDescriptor {

    private List<String> parameterTypes;
    private String returnType;

    public List<String> getParameterTypes() {
        return parameterTypes;
    }

    public MethodDescriptor() {
        this.parameterTypes = new ArrayList<>();
        this.returnType = "";
    }


    public void setParameterTypes(List<String> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    private static class MethodDescriptorParser {
        String descriptor;
        int offset;
        MethodDescriptor md;

        private MethodDescriptor parse(String descriptor) {
            this.descriptor = descriptor;
            this.offset = 0;
            this.md = new MethodDescriptor();

            this.startParams();
            this.parseParamTypes();
            this.endParams();
            this.parseReturnType();
            this.finish();
            return this.md;
        }

        private void parseReturnType() {
            if (this.readChar() == 'V') {
                this.md.returnType = "V";
                return;
            }
            this.unreadChar();
            String t = this.parseFieldType();
            if (!Strings.isNullOrEmpty(t)) {
                this.md.returnType = t;
                return;
            }
            throw new IllegalArgumentException("class file is invalid, method descriptor invalid, descriptor="+descriptor);
        }

        private void finish() {
            if (this.offset != this.descriptor.length()) {
                throw new IllegalArgumentException("class file is invalid, method descriptor invalid, descriptor="+descriptor);
            }
        }

        private void startParams() {
            if (this.readChar() != '(')
                throw new IllegalArgumentException("class file is invalid, method descriptor invalid, descriptor="+descriptor);
        }

        private void endParams() {
            if (this.readChar() != ')')
                throw new IllegalArgumentException("class file is invalid, method descriptor invalid, descriptor="+descriptor);
        }

        private char readChar() {
            return this.descriptor.charAt(offset++);
        }

        private void parseParamTypes() {
            while (true) {
                String t = this.parseFieldType();
                if (!Strings.isNullOrEmpty(t)) {
                    this.md.getParameterTypes().add(t);
                } else {
                    break;
                }
            }
        }

        private String parseFieldType() {
            char c = this.readChar();
            switch (c) {
                case 'B':
                case 'C':
                case 'D':
                case 'F':
                case 'I':
                case 'J':
                case 'S':
                case 'Z':
                case 'V':
                    return ""+c;
                case 'L':
                    return this.parseObjectType();
                case '[':
                    return this.parseArrayType();
                default:
                    this.unreadChar();
                    return "";
            }
        }

        private void unreadChar() {
            this.offset--;
        }

        private String parseObjectType() {
            String unread = descriptor.substring(this.offset, descriptor.length());
            int semicolonIndex = unread.indexOf(";");
            if (semicolonIndex < 0) {
                throw new IllegalArgumentException("class file is invalid, method descriptor invalid, descriptor=" + descriptor);
            }
            int objStart = this.offset - 1;
            int objEnd = this.offset + semicolonIndex + 1;
            this.offset = objEnd;
            return this.descriptor.substring(objStart, objEnd);
        }

        private String parseArrayType() {
            int arrStart = this.offset - 1;
            this.parseFieldType();
            int arrEnd = this.offset;
            return this.descriptor.substring(arrStart, arrEnd);
        }
    }

    public static MethodDescriptor parseMethodDescriptor(String descriptor) {
        MethodDescriptorParser parser = new MethodDescriptorParser();
        return parser.parse(descriptor);
    }

}
