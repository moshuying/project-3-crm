const passwordReg = /(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^a-zA-Z0-9]).{8,30}/
const passwordMsg="密码中必须包含大小写字母、数字、特称字符，至少8个字符，最多30个字符"
const emailReg =  /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/
const validators = {
    /**
     * 可区分全角字符/半角字符的长度校验。
     * @param min
     * @param max
     * @returns {Function}
     */
    length({min=0,max=100000000}){
        return function(rule, value,callback){
            //将一个全角字符替换成两个半角字符，以得到真实长度。
            let realLength = value.replace(/[\u0391-\uFFE5]/g,'aa').length;
            realLength <= max && realLength >= min ? callback() : max<100000000 ? callback('输入长度应在'+min+'到'+max+'个字符之间！') : callback('至少应输入'+min+'个字符！');
        }
    },
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
    emailReg,
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
    phone(){
        return function(rule, value,callback){
            let reg = /(13\d|14[579]|15[^4\D]|17[^49\D]|18\d)\d{8}/
            const msg = "您输入的手机号不正确"
            if(!reg.test(value)){
                if(!callback)return msg;
                callback(msg)
            }
        }
    },
    qq(){
        return function(rule, value,callback){
            let reg = /^[1-9][0-9]{4,9}$/gim
            const msg = "您输入的QQ号不正确"
            if(!reg.test(value)){
                if(!callback)return msg;
                callback(msg)
            }
        }
    },
    install:function(Vue) {
        Vue.prototype.validators = validators;
    }
}

export default validators
