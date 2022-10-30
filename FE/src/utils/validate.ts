export const validateFullname = (data: string) => {
    var re = /^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$/;
    return re.test(data)
}

export const validateEmail = (data: string) => {
    var re = /\S+@\S+\.\S+/;
    return re.test(data);
};

export const validatePhone = (data: string) => {
    var re = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im;
    return re.test(data);
}