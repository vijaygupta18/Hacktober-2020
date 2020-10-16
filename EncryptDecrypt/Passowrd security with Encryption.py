def pswd_encrypt(msg_txt,key):
    msg = b'msg_txt'.rjust(32)
    secret_key=b'key'
    cipher = AES.new(secret_key,AES.MODE_ECB)
    encoded=base64.b64encode(cipher.encrypt(msg))
    return encoded
    
    
def pswd_decrypt():
    decoded=cipher.decrypt(base64.b64decode(encoded))
    a=decoded.strip()
    password1=(decoded.decode()).strip()
    return password1