import { FC } from 'react';

interface Props {
    buttonText: string,
}

const SigningButton: FC<Props> = ({
    buttonText,
}) => (
    <button type="button" className={`py-4 px-6 font-poppins font-medium text-[16px] text-primary rounded-[10px]
     outline-none bg-blue-gradient hover:underline focus:underline hover:scale-105 focus:scale-105 underline-offset-4
     shadow-ms focus:shadow-sky-300 hover:shadow-sky-300 active:translate-y-[4px]`}>
        {buttonText}
    </button>
);

export default SigningButton;