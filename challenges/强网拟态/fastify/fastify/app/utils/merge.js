const whileTypes = ['boolean', 'string', 'number', 'bigint', 'symbol', 'undefined'];

const merge = (target, source) => {
    for (const key in source) {
        if(!whileTypes.includes(typeof source[key]) && !whileTypes.includes(typeof target[key])){
            if(key !== '__proto__'){
                merge(target[key], source[key]);
            }
        }else{
            target[key] = source[key];
        }
    }
}

module.exports = merge