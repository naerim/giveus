export const calculateAge = (birth: string) => {
  const birthDate = new Date(birth)
  const today = new Date()

  let year = today.getFullYear() // 년도
  let month = today.getMonth() + 1 // 월
  let date = today.getDate() // 날짜

  const createdAtDate = new Date(year + '/' + month + '/' + date)
  console.log(birthDate)
  console.log(createdAtDate)

  const age = createdAtDate.getFullYear() - birthDate.getFullYear()

  if (
    createdAtDate.getMonth() < birthDate.getMonth() ||
    (createdAtDate.getMonth() === birthDate.getMonth() &&
      createdAtDate.getDate() < birthDate.getDate())
  ) {
    return age - 1
  } else {
    return age
  }
}

export const divideBirth = (inferText: string) => {
  const processedText = inferText.replace(/\s/g, '').split('-')
  let strBirth: any = processedText[0].split('')
  strBirth.splice(4, 0, '-')
  strBirth.splice(2, 0, '-')
  if (strBirth[0] < 3) {
    strBirth.unshift('20')
  } else {
    strBirth.unshift('19')
  }
  strBirth = strBirth.join('')
  return strBirth
}

export const TodayDate = () => {
  const today = new Date()
  const year = today.getFullYear()
  const month = String(today.getMonth() + 1).padStart(2, '0')
  const day = String(today.getDate()).padStart(2, '0')

  return `${year}-${month}-${day}`
}
