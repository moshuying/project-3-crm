const passwordReg = /(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^a-zA-Z0-9]).{8,30}/
const passwordMsg="密码中必须包含大小写字母、数字、特称字符，至少8个字符，最多30个字符"
const emailReg =  /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/
const validators = {
    passwordReg,
    passwordMsg,
    password(){
        return function(rule, value,callback){
            let reg = passwordReg
            const msg = passwordMsg
            if(!reg.test(value)){
                if(!callback)return msg;
                callback(msg)
            }
        }
    },
    email(){
        return function(rule, value,callback){
            let reg =emailReg;
            const msg = "您输入的email地址不正确"
            if(!reg.test(value)){
                if(!callback) return msg;
                callback(msg)
            }
        }
    },
    emailReg,
    phoneReg:/^1(3\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\d|9[0-35-9])\d{8}$/,
    qqReg:/^[1-9][0-9]{4,9}$/gim,
    qqMsg:"您输入的QQ号不正确",
    install:function(Vue) {
        Vue.prototype.validators = validators;
    }
}

export default validators
