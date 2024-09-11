export default function validateEmail(emailToValidate) {
  const emailRegex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i
  if (emailRegex.test(emailToValidate)) {
    return true
  } else {
    return false
  }
}
